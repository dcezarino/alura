package br.alura.com.vollmed.domain.consulta;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsulta(

        @NotNull(message = "O id da consulta não pode ser nulo.")
        Long idConsulta,

        @NotNull(message = "O motivo do cancelamento deve ser informado.")
        MotivoCancelamento motivo) {

}
