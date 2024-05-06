package br.alura.com.vollmed.domain.consulta.validacoes.agendamento;

import br.alura.com.vollmed.domain.ValidacaoException;
import br.alura.com.vollmed.domain.consulta.DadosAgendamentoConsulta;
import br.alura.com.vollmed.repository.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsultas {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dados) {
        var pacienteEstaAtivo = pacienteRepository.findAtivoById(dados.idPaciente());

        if(!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta n�o pode ser agendada para um paciente inativo");
        }
    }
}