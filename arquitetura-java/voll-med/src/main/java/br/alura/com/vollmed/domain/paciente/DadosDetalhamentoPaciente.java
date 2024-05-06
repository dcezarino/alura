package br.alura.com.vollmed.domain.paciente;

import br.alura.com.vollmed.domain.endereco.Endereco;

public record DadosDetalhamentoPaciente(Long id, String cpf, String nome, String email, String telefone, Endereco endereco) {

        public DadosDetalhamentoPaciente(Paciente paciente) {
                this(paciente.getId(), paciente.getCpf(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getEndereco());
        }
}