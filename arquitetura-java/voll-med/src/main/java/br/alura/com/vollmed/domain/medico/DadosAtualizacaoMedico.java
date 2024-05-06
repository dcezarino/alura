package br.alura.com.vollmed.domain.medico;

import br.alura.com.vollmed.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DadosAtualizacaoMedico(

        @NotNull(message = "O id é obrigatório.")
        @Positive
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {


}
