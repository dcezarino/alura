package med.voll.api.medico;

import med.voll.api.endereco.DadosEndereco;

/*
Java cria uma classe imutável quando usamos record;
Campos viram atributos, métodos getters, setters e construtores são gerados;
Spring dá suporte para gerar essas tarefas com facilidade usando record;
Esta classe representa um DTO;
 */
public record DadosCadastroMedico(String nome, String email, String crm, Especialidade especialidade, DadosEndereco endereco) {
}
