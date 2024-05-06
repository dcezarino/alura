package br.alura.com.vollmed.domain.consulta;

import br.alura.com.vollmed.domain.medico.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(

        @NotNull(message = "o id do médico é obrigatório.")
        Long idMedico,

        @NotNull(message = "o id do paciente é obrigatório.")
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime data,

        @NotNull(message = "A especialidade do médico é obrigatória.")
        Especialidade especialidade) {

}
