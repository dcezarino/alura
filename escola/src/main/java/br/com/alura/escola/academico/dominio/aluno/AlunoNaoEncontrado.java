package br.com.alura.escola.academico.dominio.aluno;

import br.com.alura.escola.shared.dominio.CPF;

public class AlunoNaoEncontrado extends RuntimeException {
	
	public AlunoNaoEncontrado(CPF cpf) {
		
		super("Aluno n�o encontrado com o cpf: " + cpf.getNumero());
		
	}
	
}
