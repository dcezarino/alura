package alura.jmilhas.api.domain.depoimento;

public record DadosDetalhamentoDepoimento(Long id, String foto, String descricao, String nomeResponsavel) {
	
	public DadosDetalhamentoDepoimento(Depoimento depoimento) {
		
		this(depoimento.getId(), depoimento.getFoto(), depoimento.getDescricao(), depoimento.getNomeResponsavel());
		
	}

}
