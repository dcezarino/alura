package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    /*
    Injeção de Dependências: indicar com a anotação "@Autowired" para
    que o Spring instancie esse atributo "medicoRepository", já que esse atributo
    é uma interface repository que deve ser reconhecida e recarregada,  então cria
    o objeto e passa para o controller automaticamente.
     */
    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {

        medicoRepository.save(new Medico(dados));

    }

    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {

        /*
        Usando stream para efetuar a conversão do retorno findAll() que seria "Medico" para
        "DadosListagemMedico", método implementado na classe DadosListagemMedico().
        Conversão: Lista de "Medico" para Lista de "DadosListagemMedico"
         */

        //return medicoRepository.findAll(paginacao).stream().map(DadosListagemMedico::new).toList();

        return medicoRepository.findAll(paginacao).map(DadosListagemMedico::new);

    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {

        var medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

    }

}
