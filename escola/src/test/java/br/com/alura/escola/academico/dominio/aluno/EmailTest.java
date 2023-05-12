package br.com.alura.escola.academico.dominio.aluno;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.alura.escola.academico.dominio.aluno.Email;

class EmailTest {

	@Test
	void naoDeveriaCriarEmailsComEnderecosInvalidos() {
		
		assertThrows(IllegalArgumentException.class, () -> new Email(null));
		
		assertThrows(IllegalArgumentException.class, () -> new Email(""));
		
		assertThrows(IllegalArgumentException.class, () -> new Email("emailinvalido"));
	
	}
	
	@Test
	void deveriaCriarEmailComEnderecoValido() {
		
		Email email = new Email("teste@teste.com.br");
		
		assertEquals(email.getEndereco(), "teste@teste.com.br");
	
	}

}