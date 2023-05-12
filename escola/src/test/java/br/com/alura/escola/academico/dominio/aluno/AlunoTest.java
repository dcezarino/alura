package br.com.alura.escola.academico.dominio.aluno;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.escola.academico.dominio.aluno.Aluno;
import br.com.alura.escola.academico.dominio.aluno.Email;
import br.com.alura.escola.academico.dominio.aluno.TelefoneMaxException;
import br.com.alura.escola.shared.dominio.CPF;

class AlunoTest {
	
	private Aluno aluno;
	
	@BeforeEach
	void beforeEach() {
		
		this.aluno = new Aluno (new CPF("123.456.789-00"), 
						"Aluno Modelo", 
						 new Email("alunomodelo@teste.com"));
		
	}
	
	@Test
	void deveriaPermitirAdicionarTelefone() throws TelefoneMaxException {
		
		this.aluno.adicionarTelefone("19", "792058888");
		
		assertEquals(1, this.aluno.getTelefones().size());
		
	}
	
	@Test
	void deveriaPermitirAdicionarDoisTelefones() throws TelefoneMaxException {
		
		this.aluno.adicionarTelefone("19", "792058888");
		this.aluno.adicionarTelefone("19", "792058887");
		
		assertEquals(2, this.aluno.getTelefones().size());
		
	}
	
	@Test
	void naoDeveriaPermitirAdicionarTresTelefones() throws TelefoneMaxException {
		
		assertThrows(TelefoneMaxException.class, () -> {
			
			this.aluno.adicionarTelefone("19", "792058888");
			this.aluno.adicionarTelefone("19", "792058887");
			this.aluno.adicionarTelefone("19", "792058886");	
		
		});
	
	
	}
}