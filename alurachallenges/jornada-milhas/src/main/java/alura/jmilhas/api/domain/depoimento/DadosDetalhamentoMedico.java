package alura.jmilhas.api.domain.depoimento;

public record DadosDetalhamentoMedico(Long id, String foto, String descricao, String nomeResponsavel) {
	
	public DadosDetalhamentoMedico(Depoimento depoimento) {
		
		this(depoimento.getId(), depoimento.getFoto(), depoimento.getDescricao(), depoimento.getNomeResponsavel());
		
	}

}
