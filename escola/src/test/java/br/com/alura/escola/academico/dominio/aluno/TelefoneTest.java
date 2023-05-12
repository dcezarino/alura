package br.com.alura.escola.academico.dominio.aluno;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.alura.escola.academico.dominio.aluno.Telefone;

class TelefoneTest {

	@Test
	void naoDeveriaCadastrarTelefoneComDadosInvalidos() {
		
		assertThrows(IllegalArgumentException.class, () -> new Telefone("",""));
		
		assertThrows(IllegalArgumentException.class, () -> new Telefone("","numero"));
		
		assertThrows(IllegalArgumentException.class, () -> new Telefone("ddd",""));
		
		assertThrows(IllegalArgumentException.class, () -> new Telefone("ddd","numero"));
		
	}
	
	@Test
	void deveriaCadastrarTelefoneComDadosValidos() {
		
		Telefone telefone = new Telefone("19", "991554488");
		assertEquals(telefone.getDdd(), "19");
		assertEquals(telefone.getNumero(), "991554488");
		
	}

}
