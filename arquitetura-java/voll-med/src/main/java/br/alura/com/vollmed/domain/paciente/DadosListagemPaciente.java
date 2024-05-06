package br.alura.com.vollmed.domain.paciente;

import br.alura.com.vollmed.domain.endereco.Endereco;

public record DadosListagemPaciente(Long id, String cpf, String nome, String email, String telefone, Endereco endereco) {

    public DadosListagemPaciente(Paciente paciente) {

        this(paciente.getId(), paciente.getCpf(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getEndereco());

    }

}