package br.alura.com.vollmed.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record DadosEndereco(
        @NotBlank(message = "O logradouro é obrigatório.")
        String logradouro,
        @NotBlank(message = "O bairro é obrigatório.")
        String bairro,
        @NotBlank(message = "A cidade é obrigatória.")
        String cidade,
        @NotBlank(message = "O estado é obrigatório.")
        String uf,
        @NotBlank(message = "O cep é obrigatório.")
        @Pattern(regexp = "\\d{8}")
        String cep,
        @Positive
        int numero,
        String complemento

) {
}
