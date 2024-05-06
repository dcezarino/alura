package br.alura.com.vollmed.domain.paciente;

import br.alura.com.vollmed.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record DadosAtualizacaoPaciente(

        @NotNull(message = "O id é obrigatório.")
        @Positive
        Long id,
        String nome,
        @Email
        String email,
        @Pattern(regexp = "^\\(\\d{2}\\)\\s\\d{5}-\\d{4}$", message = "Número de telefone inválido.")
        String telefone,
        DadosEndereco endereco) {


}
