package br.alura.com.vollmed.service.consulta;

import br.alura.com.vollmed.domain.ValidacaoException;
import br.alura.com.vollmed.domain.consulta.Consulta;
import br.alura.com.vollmed.domain.consulta.DadosAgendamentoConsulta;
import br.alura.com.vollmed.domain.consulta.DadosCancelamentoConsulta;
import br.alura.com.vollmed.domain.consulta.DadosDetalhamentoConsulta;
import br.alura.com.vollmed.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsultas;
import br.alura.com.vollmed.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import br.alura.com.vollmed.domain.medico.DadosListagemMedico;
import br.alura.com.vollmed.domain.medico.Medico;
import br.alura.com.vollmed.repository.consulta.ConsultaRepository;
import br.alura.com.vollmed.repository.medico.MedicoRepository;
import br.alura.com.vollmed.repository.paciente.PacienteRepository;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsultas> validadores;

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){

        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Id do médico informado não existe.");
        }

        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do paciente informado não existe.");
        }

        validadores.forEach( v-> v.validar(dados));

        var medico = escolherMedico(dados);

        if(medico == null){
            throw new ValidacaoException("Não existe médico disponível nessa data.");
        }

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());

        var consulta = new Consulta(null, medico, paciente, dados.data(), null);

        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);

    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {

        if (dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idPaciente());
        }

        if(dados.especialidade() == null) {
            throw new ValidacaoException("Especialidae é obrigatória quando médico não for escolhido.");
        }

        return medicoRepository.escolherMedicoAleatoriaLivreNaData(dados.especialidade(), dados.data());

    }

    public void cancelar(DadosCancelamentoConsulta dados) {

        if(!consultaRepository.existsById(dados.idConsulta())){
            throw new ValidacaoException("Id da consulta informado não existe.");
        }

        validadoresCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());

    }

    public ResponseEntity<Page<?>> findAll(Pageable paginacao) {
        var page = consultaRepository.findAllByMotivoCancelamento(paginacao).map(DadosDetalhamentoConsulta::new);
        return ResponseEntity.ok(page);
    }

}