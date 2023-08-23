package br.com.alura.loja.orcamento;

import br.com.alura.loja.DomainException;
import br.com.alura.loja.http.HttpAdapter;

import java.util.Map;


public class RegistroOrcamento {

	private HttpAdapter httpAdapter;

	public RegistroOrcamento(HttpAdapter httpAdapter) {
		this.httpAdapter = httpAdapter;
	}

	public void registrar(Orcamento orcamento) {
		if (!orcamento.isFinalizado()) {
			throw new DomainException("Orcamento nao finalizado nao pode ser registrado!");
		}

		String urlApi = "http://api.xyz/orcamento";
		Map<String, Object> dadosApi = Map.of(
			"valor", orcamento.getValor(),
			"quantidadeItens", orcamento.getQuantidadeItens()
		);

		httpAdapter.post(urlApi, dadosApi);
	}

}
