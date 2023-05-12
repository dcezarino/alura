package br.com.alura.escola.academico.dominio.aluno;

public interface CifradorDeSenha {	
	
	String CifrarSenha(String senha);
	
	boolean validarSenhaCrifrada(String senhaCifrada, String senha);
		
}
