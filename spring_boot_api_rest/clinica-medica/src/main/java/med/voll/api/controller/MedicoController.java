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
    Inje��o de Depend�ncias: indicar com a anota��o "@Autowired" para
    que o Spring instancie esse atributo "medicoRepository", j� que esse atributo
    � uma interface repository que deve ser reconhecida e recarregada,  ent�o cria
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
        Usando stream para efetuar a convers�o do retorno findAll() que seria "Medico" para
        "DadosListagemMedico", m�todo implementado na classe DadosListagemMedico().
        Convers�o: Lista de "Medico" para Lista de "DadosListagemMedico"
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
