package br.alura.com.vollmed.domain.medico;

import br.alura.com.vollmed.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedico(
        @NotBlank(message = "O nome é obrigatório.")
        String nome,
        @NotBlank(message = "O e-mail é obrigatório.")
        @Email
        String email,
        @NotNull(message = "A especialidade do médico é obrigatória.")
        Especialidade especialidade,
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotBlank(message = "O telefone é obrigatório.")
        @Pattern(regexp = "^\\(\\d{2}\\)\\s\\d{5}-\\d{4}$", message = "Número de telefone inválido.")
        String telefone,
        @NotNull(message = "O endereço do médico é obrigatório.")
        DadosEndereco dadosEndereco

) {
}
