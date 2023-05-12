package br.com.alura.escola.academico.aplicacao.aluno.matricular;

import br.com.alura.escola.academico.dominio.aluno.Aluno;
import br.com.alura.escola.academico.dominio.aluno.AlunoMatriculado;
import br.com.alura.escola.academico.dominio.aluno.RepositorioDeAlunos;
import br.com.alura.escola.shared.dominio.evento.PublicadorDeEventos;

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