package br.com.alura.escola.academico.dominio.aluno;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.alura.escola.shared.dominio.CPF;

class CPFTest {

	@Test
	void naoDeveriaCriarCPFsComNumerosInvalidos() {
		
		assertThrows(IllegalArgumentException.class, () -> new CPF(""));
		
		assertThrows(IllegalArgumentException.class, () -> new CPF(" "));
		
		assertThrows(IllegalArgumentException.class, () -> new CPF("1234567891"));
		
		assertThrows(IllegalArgumentException.class, () -> new CPF("cpfinvalido"));
		
	}
	
	@Test
	void deveriaCriarCPFComNumeroValido() {
		
		CPF cpf = new CPF("12345678912");		
		assertEquals(cpf.getNumero(), "12345678912");
		
	}

}
