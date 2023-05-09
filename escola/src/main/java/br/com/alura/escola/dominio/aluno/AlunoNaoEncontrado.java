package br.com.alura.escola.dominio.aluno;

public class AlunoNaoEncontrado extends RuntimeException {
	
	public AlunoNaoEncontrado(CPF cpf) {
		super("Aluno n�o encontrado com o cpf: " + cpf.getNumero());
	}
	
}
