package br.alura.com.vollmed.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacaoUsuario(
        @NotBlank(message = "O login não pode ser vazio.")
        String login,
        @NotBlank(message = "A senha não pode ser vazia.")
        String senha) {
}
