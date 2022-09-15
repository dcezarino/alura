package leilao;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.alura.leilao.dao.LeilaoDao;
import br.com.alura.leilao.model.Leilao;

public class HelloWorldMockito {
	
	@Test
	public void hello() {
		
		// Lê a classe LeilaoDao.class e dinamicamente cria uma classe em memória.
		// Simulando o comportamento de uma classe.
		LeilaoDao mockLeilaoDao = Mockito.mock(LeilaoDao.class);
		List<Leilao> buscarTodos = mockLeilaoDao.buscarTodos();
		Assert.assertTrue(buscarTodos.isEmpty());
		
	}

}


