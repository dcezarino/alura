package br.alura.com.vollmed.controller.medico;

import br.alura.com.vollmed.domain.medico.DadosAtualizacaoMedico;
import br.alura.com.vollmed.domain.medico.DadosCadastroMedico;
import br.alura.com.vollmed.domain.medico.DadosDetalhamentoMedico;
import br.alura.com.vollmed.service.medico.MedicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @Transactional
    @PostMapping
    public ResponseEntity cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dadosCadastroMedico, UriComponentsBuilder uriBuilder) {
        var savedMedico = medicoService.cadastrarMedico(dadosCadastroMedico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(savedMedico.id()).toUri();

        return ResponseEntity.created(uri).body(savedMedico);
    }

    @GetMapping
    public ResponseEntity<Page<?>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return medicoService.findAll(paginacao);
    }

    @Transactional
    @PutMapping
    public ResponseEntity<?> atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {

        var medico = medicoService.findById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));

    }

}
