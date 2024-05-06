package br.alura.com.vollmed.service.medico;

import br.alura.com.vollmed.domain.medico.DadosCadastroMedico;
import br.alura.com.vollmed.domain.medico.DadosDetalhamentoMedico;
import br.alura.com.vollmed.domain.medico.DadosListagemMedico;
import br.alura.com.vollmed.domain.medico.Medico;
import br.alura.com.vollmed.repository.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public DadosDetalhamentoMedico cadastrarMedico(DadosCadastroMedico dadosCadastroMedico) {
        var medico = new Medico(dadosCadastroMedico);
        medicoRepository.save(medico);
        return new DadosDetalhamentoMedico(medico);
    }

    public ResponseEntity<Page<?>> findAll(Pageable paginacao) {
        var page = medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
    }

    public Medico findById(Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        return medico;
    }
}
