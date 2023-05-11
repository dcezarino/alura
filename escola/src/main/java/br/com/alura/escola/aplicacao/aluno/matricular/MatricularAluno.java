package br.com.alura.escola.aplicacao.aluno.matricular;

import br.com.alura.escola.dominio.aluno.Aluno;
import br.com.alura.escola.dominio.aluno.RepositorioDeAlunos;

public class MatricularAluno {
	
	private final RepositorioDeAlunos repositorioDeAlunos;

	public MatricularAluno(RepositorioDeAlunos repositorioDeAlunos) {	
		
		this.repositorioDeAlunos = repositorioDeAlunos;
		
	}	
	
	public void executa(MatricularAlunoDto dados) {
		
		Aluno novo = dados.criarAluno();
		repositorioDeAlunos.matricular(novo);
		
	}
	
}