package alura.jmilhas.api.domain.depoimento;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroDepoimento(
		
        @NotBlank
        String foto,

		@NotBlank
        String descricao,
        
        @NotBlank
        String nomeResponsavel) {

}