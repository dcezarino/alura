package br.com.alura.escola.academico.dominio.aluno;

public class Email {
	
	// Value object
	private String endereco;
	
	public Email(String endereco) {
		
		if (endereco == null || !endereco.matches("^[\\w-_.+]*[\\w-_.]@[\\w]+[\\w.-]+[\\w-]$")) {
			
			throw new IllegalArgumentException("E-mail inválido.");			
			
		}
		
		this.endereco = endereco;

	}

	public String getEndereco() {
		return endereco;
	}

}