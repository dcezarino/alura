package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;
import org.hibernate.validator.constraints.br.CPF;

/*
Java cria uma classe imut�vel quando usamos record;
Campos viram atributos, m�todos getters, setters e construtores s�o gerados;
Spring d� suporte para gerar essas tarefas com facilidade usando record;
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
        al�m de validar o atributo DadosEndereco com as suas respectivas
        anota��es do bean validation.
         */
        @NotNull
        @Valid
        DadosEndereco endereco) {
}