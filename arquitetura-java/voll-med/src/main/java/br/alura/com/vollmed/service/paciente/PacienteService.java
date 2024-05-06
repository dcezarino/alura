package br.alura.com.vollmed.service.paciente;

import br.alura.com.vollmed.domain.paciente.DadosCadastroPaciente;
import br.alura.com.vollmed.domain.paciente.DadosDetalhamentoPaciente;
import br.alura.com.vollmed.domain.paciente.DadosListagemPaciente;
import br.alura.com.vollmed.domain.paciente.Paciente;
import br.alura.com.vollmed.repository.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public DadosDetalhamentoPaciente cadastrarPaciente(DadosCadastroPaciente dadosCadastroPaciente) {
        var paciente = new Paciente(dadosCadastroPaciente);
        pacienteRepository.save(paciente);
        return new DadosDetalhamentoPaciente(paciente);
    }

    public ResponseEntity<Page<?>> findAll(Pageable paginacao) {
        var page = pacienteRepository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
        return ResponseEntity.ok(page);
    }

    public Paciente findById(Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        return paciente;
    }
}
