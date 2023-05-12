package br.com.alura.escola.academico.dominio.aluno;

public class Telefone {
	
	private String ddd;
	private String numero;
	
	public Telefone(String ddd, String numero) {	
		
		if (ddd == null || numero == null) {			
			throw new IllegalArgumentException("DDD e N�mero s�o obrigat�rios.");
		}
		
		if (ddd == null || !ddd.matches("^[1-9]{2}$")) {			
			throw new IllegalArgumentException("DDD inv�lido.");			
		}
		
		if (numero == null || !numero.matches("^\\(?([2-9]{1}[0-9]{3,4})\\)?[-]?([0-9]{4})$")) {			
			throw new IllegalArgumentException("N�mero inv�lido.");					
		}
		
		this.ddd = ddd;
		this.numero = numero;
		
	}
	
	public String getDdd() {
		return ddd;
	}

	public String getNumero() {
		return numero;
	}

}
