package br.alura.com.vollmed.controller.consulta;

import br.alura.com.vollmed.domain.consulta.DadosAgendamentoConsulta;
import br.alura.com.vollmed.domain.consulta.DadosCancelamentoConsulta;
import br.alura.com.vollmed.service.consulta.AgendaDeConsultas;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agendaDeConsultas;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {

        var dto = agendaDeConsultas.agendar(dados);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {

        agendaDeConsultas.cancelar(dados);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<?>> findAll(@PageableDefault(size = 10, sort = {"data"}) Pageable paginacao) {
        return agendaDeConsultas.findAll(paginacao);
    }

}