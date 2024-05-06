package br.alura.com.vollmed.domain.paciente;

import br.alura.com.vollmed.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

public record DadosCadastroPaciente (

        @NotBlank(message = "O cpf é obrigatório.")
        @CPF
        String cpf,
        @NotBlank(message = "O nome é obrigatório.")
        String nome,
        @NotBlank(message = "O e-mail é obrigatório.")
        @Email
        String email,
        @NotBlank(message = "O telefone é obrigatório.")
        @Pattern(regexp = "^\\(\\d{2}\\)\\s\\d{5}-\\d{4}$", message = "Número de telefone inválido.")
        String telefone,

        DadosEndereco dadosEndereco

){
}
