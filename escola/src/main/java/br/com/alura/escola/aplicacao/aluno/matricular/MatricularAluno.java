package br.com.alura.escola.aplicacao.aluno.matricular;

import br.com.alura.escola.dominio.PublicadorDeEventos;
import br.com.alura.escola.dominio.aluno.Aluno;
import br.com.alura.escola.dominio.aluno.AlunoMatriculado;
import br.com.alura.escola.dominio.aluno.RepositorioDeAlunos;

public class MatricularAluno {
	
	private final RepositorioDeAlunos repositorioDeAlunos;
	private final PublicadorDeEventos publicadorDeEventos;

	public MatricularAluno(RepositorioDeAlunos repositorioDeAlunos, PublicadorDeEventos publicadorDeEventos) {	
		
		this.repositorioDeAlunos = repositorioDeAlunos;
		this.publicadorDeEventos = publicadorDeEventos;
		
	}	
	
	public void executa(MatricularAlunoDto dados) {
		
		Aluno novo = dados.criarAluno();
		repositorioDeAlunos.matricular(novo);
		
		AlunoMatriculado evento = new AlunoMatriculado(novo.getCpf());
		publicadorDeEventos.publicar(evento);		
		
	}
	
}