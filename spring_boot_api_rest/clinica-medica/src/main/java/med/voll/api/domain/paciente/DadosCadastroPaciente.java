package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosAtualizacaoEndereco;
import org.hibernate.validator.constraints.br.CPF;

/*
Java cria uma classe imutável quando usamos record;
Campos viram atributos, métodos getters, setters e construtores são gerados;
Spring dá suporte para gerar essas tarefas com facilidade usando record;
Esta classe representa um DTO;
 */
public record DadosCadastroPaciente (

        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @CPF
        String cpf,

        /*
        @Valid, bean validation valida o DTO DadosCadastroMedico(),
        além de validar o atributo DadosEndereco com as suas respectivas
        anotações do bean validation.
         */
        @NotNull
        @Valid
        DadosAtualizacaoEndereco endereco) {
}