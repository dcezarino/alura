package alura.jmilhas.api.domain.depoimento;

public record DadosListagemDepoimento(Long id, String foto, String descricao, String nomeResponsavel) {
	
	public DadosListagemDepoimento(Depoimento depoimento) {
		
		this(depoimento.getId(), depoimento.getFoto(), depoimento.getDescricao(), depoimento.getNomeResponsavel());
		
	}

}