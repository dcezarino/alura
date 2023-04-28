package br.com.alura.viacep.modelos;

public class Endereco {
	
	private String cep;
	private String logradouro;
	private String bairro;
	private String localidade;
	private String uf;
	
	public Endereco(String cep, String logradouro, String bairro, String localidade, String uf) {	
		
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		
	}

	public Endereco(EnderecoRecord meuEndereco) {
		
		this.cep = meuEndereco.cep();
		this.logradouro = meuEndereco.logradouro();
		this.bairro = meuEndereco.bairro();
		this.localidade = meuEndereco.localidade();
		this.uf = meuEndereco.uf();
				
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public String getUf() {
		return uf;
	}

	@Override
	public String toString() {
		return "Endereco [cep=" + cep + ", logradouro=" + logradouro + ", bairro=" + bairro + ", localidade="
				+ localidade + ", uf=" + uf + "]";
	}
	
	
}