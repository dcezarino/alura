package br.alura.com.vollmed.domain.consulta.validacoes.agendamento;

import br.alura.com.vollmed.domain.ValidacaoException;
import br.alura.com.vollmed.domain.consulta.DadosAgendamentoConsulta;
import br.alura.com.vollmed.repository.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsultas{

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DadosAgendamentoConsulta dados) {

        // Escolha o médico (opcional)
        if(dados.idMedico() == null){
            return;
        }

        var medicoEstaAtivo = medicoRepository.findAtivoById(dados.idMedico());
        if(!medicoEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agenda com médico inativo");

        }
    }

}