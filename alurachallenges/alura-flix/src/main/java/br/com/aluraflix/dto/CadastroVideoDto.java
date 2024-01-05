package br.com.aluraflix.dto;

import jakarta.validation.constraints.NotBlank;

public record CadastroVideoDto (

        @NotBlank
        String titulo,
        @NotBlank
        String descricao,
        @NotBlank
        String url) {

}
