package br.com.alura.escola.academico.aplicacao.aluno.matricular;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.alura.escola.academico.aplicacao.aluno.matricular.MatricularAluno;
import br.com.alura.escola.academico.aplicacao.aluno.matricular.MatricularAlunoDto;
import br.com.alura.escola.academico.dominio.aluno.Aluno;
import br.com.alura.escola.academico.dominio.aluno.LogDeAlunoMatriculado;
import br.com.alura.escola.academico.infra.aluno.RepositorioDeAlunosEmMemoria;
import br.com.alura.escola.shared.dominio.CPF;
import br.com.alura.escola.shared.dominio.evento.PublicadorDeEventos;

class MatricularAlunoTest {

	@Test
	void alunoDeveriaSerPersistido() {
		
		RepositorioDeAlunosEmMemoria repositorioDeAlunosEmMemoria = new RepositorioDeAlunosEmMemoria();
		
		PublicadorDeEventos publicador = new PublicadorDeEventos();
		publicador.adicionar(new LogDeAlunoMatriculado());
		
		MatricularAluno useCase = new MatricularAluno(repositorioDeAlunosEmMemoria, publicador);
		
		MatricularAlunoDto dados = new MatricularAlunoDto("Aluno Modelo", "123.456.789-00", "alunomodelo@teste.com");
		useCase.executa(dados);
		
		MatricularAlunoDto newDados = new MatricularAlunoDto("Aluno Modelo 02", "123.456.789-01", "alunomodelo02@teste.com");
		useCase.executa(newDados);
		
		Aluno aluno = repositorioDeAlunosEmMemoria.buscarPorCPF(new CPF("123.456.789-00"));
		
		assertEquals("Aluno Modelo", aluno.getNome());
		assertEquals("123.456.789-00", aluno.getCpf().toString());
		assertEquals("alunomodelo@teste.com", aluno.getEmail());
		
		Aluno newAluno = repositorioDeAlunosEmMemoria.buscarPorCPF(new CPF("123.456.789-01"));
		
		assertEquals("Aluno Modelo 02", newAluno.getNome());
		assertEquals("123.456.789-01", newAluno.getCpf().toString());
		assertEquals("alunomodelo02@teste.com", newAluno.getEmail());
		
	}
	
}