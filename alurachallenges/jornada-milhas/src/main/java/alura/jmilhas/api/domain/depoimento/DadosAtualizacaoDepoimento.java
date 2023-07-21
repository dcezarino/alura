package alura.jmilhas.api.domain.depoimento;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoDepoimento(

		@NotNull
		Long id,
        String foto,
		String descricao,
        String nomeResponsavel) {		

}
