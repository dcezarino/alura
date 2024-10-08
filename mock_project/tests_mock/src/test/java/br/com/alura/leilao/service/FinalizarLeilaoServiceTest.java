package br.com.alura.leilao.service;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.alura.leilao.dao.LeilaoDao;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;

class FinalizarLeilaoServiceTest {

	private FinalizarLeilaoService finalizarLeilaoService;
	
	@Mock
	private LeilaoDao leilaoDao;
	
	@Mock
	private EnviadorDeEmails enviadorDeEmails;
	
	@BeforeEach
	public void beforeEach() {
		
		MockitoAnnotations.initMocks(this);		
		this.finalizarLeilaoService = new FinalizarLeilaoService(leilaoDao, enviadorDeEmails);
		
	}
	
	@Test
	void deveriaFinalizarUmLeilao() {
		
		// Entrada, gera a lista de leiloes através do método (leiloes)
		List<Leilao> leiloes = leiloes();
		
		// Quando o método leilaoDAO.buscarLeiloesExpirados() for chamado, então retorne a lista de leiloes
		when(leilaoDao.buscarLeiloesExpirados())
		.thenReturn(leiloes);
				
		// Quando o método finalizarLeilaoService.finalizarLeiloesExpirados() for chamado o retorno dele vai ser leiloes
		// leiloes.buscarLeiloesExpirados();
		finalizarLeilaoService.finalizarLeiloesExpirados();
		
		Leilao leilao = leiloes.get(0);
		
		Assert.assertTrue(leilao.isFechado());
		Assert.assertEquals(new BigDecimal("900"), leilao.getLanceVencedor().getValor());
		// Verifica se o dao foi chamado o método salver passando como parâmetro o leilao.
		Mockito.verify(leilaoDao).salvar(leilao);
		
	}
	
	@Test
	void deveriaEnviarEmailParaVencedorDoLeilao() {
		
		List<Leilao> leiloes = leiloes();
		
		when(leilaoDao.buscarLeiloesExpirados())
		.thenReturn(leiloes);
				
		finalizarLeilaoService.finalizarLeiloesExpirados();
		
		Leilao leilao = leiloes.get(0);
		
		Lance lanceVencedor = leilao.getLanceVencedor();

		Mockito.verify(enviadorDeEmails).enviarEmailVencedorLeilao(lanceVencedor);
		
	}
	
	@Test
	void naoDeveriaEnviarEmailParaVencedorDoLeilaoEmCasoDeErroAoEncerraOLeilao() {
		
		List<Leilao> leiloes = leiloes();
		
		when(leilaoDao.buscarLeiloesExpirados())
		.thenReturn(leiloes);
		
		Mockito.when(leilaoDao.salvar(Mockito.any())).thenThrow(RuntimeException.class);
		
		try {
			finalizarLeilaoService.finalizarLeiloesExpirados();			
			Mockito.verifyNoInteractions(enviadorDeEmails);		
		} catch (Exception e) {

		}
		
	}
	
	// Método de entrada
	private List<Leilao> leiloes() {
		
		List<Leilao> lista = new ArrayList<>();
		
		Leilao leilao = new Leilao("Celular", new BigDecimal("500"), new Usuario("Fulano"));
		
		Lance primeiro = new Lance(new Usuario("Beltrano"), new BigDecimal("600"));
		
		Lance segundo = new Lance(new Usuario("Ciclano"), new BigDecimal("900"));
		
		leilao.propoe(primeiro);
		leilao.propoe(segundo);
		
		lista.add(leilao);
		
		return lista;
		
	}

}