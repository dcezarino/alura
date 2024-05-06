package br.alura.com.vollmed.controller.paciente;

import br.alura.com.vollmed.domain.paciente.DadosAtualizacaoPaciente;
import br.alura.com.vollmed.domain.paciente.DadosCadastroPaciente;
import br.alura.com.vollmed.domain.paciente.DadosDetalhamentoPaciente;
import br.alura.com.vollmed.service.paciente.PacienteService;
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
@RequestMapping("/pacientes")

public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Transactional
    @PostMapping
    public ResponseEntity cadastrarPaciente(@RequestBody @Valid DadosCadastroPaciente dadosCadastroPaciente, UriComponentsBuilder uriBuilder) {
        var savedPaciente = pacienteService.cadastrarPaciente(dadosCadastroPaciente);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(savedPaciente.id()).toUri();

        return ResponseEntity.created(uri).body(savedPaciente);
    }

    @GetMapping
    public ResponseEntity<Page<?>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return pacienteService.findAll(paginacao);
    }

    @Transactional
    @PutMapping
    public ResponseEntity<?> atualizarDadosPaciente(@RequestBody @Valid DadosAtualizacaoPaciente dados) {

        var paciente = pacienteService.findById(dados.id());
        paciente.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));

    }


}
