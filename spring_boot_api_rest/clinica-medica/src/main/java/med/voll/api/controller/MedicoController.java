package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
@SecurityRequirement(name = "bearer-key")
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
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {

        var medico = new Medico(dados);
        medicoRepository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));

    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {

        /*
        Usando stream para efetuar a conversão do retorno findAll() que seria "Medico" para
        "DadosListagemMedico", método implementado na classe DadosListagemMedico().
        Conversão: Lista de "Medico" para Lista de "DadosListagemMedico"
         */

        //return medicoRepository.findAll(paginacao).stream().map(DadosListagemMedico::new).toList();

        var page = medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);

    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {

        var medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));

    }

    /*
    @PathVariable (variável da url/caminho), leva em consideração
    o {id} que está vindo como parâmetro na requisição de exclusão.

    Por padrão quando processamos uma exclusão a resposta
    deve ser 204 - No Content.
    */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {

        var medico = medicoRepository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {

        var medico = medicoRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));

    }

}