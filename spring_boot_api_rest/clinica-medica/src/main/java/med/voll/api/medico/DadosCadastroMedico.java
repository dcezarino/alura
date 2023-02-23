package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;

/*
Java cria uma classe imutável quando usamos record;
Campos viram atributos, métodos getters, setters e construtores são gerados;
Spring dá suporte para gerar essas tarefas com facilidade usando record;
Esta classe representa um DTO;
 */
public record DadosCadastroMedico(

        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Especialidade especialidade,

        /*
        @Valid, bean validation valida o DTO DadosCadastroMedico(),
        além de validar o atributo DadosEndereco com as suas respectivas
        anotações do bean validation.
         */
        @NotNull
        @Valid
        DadosEndereco endereco) {
}
