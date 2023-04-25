package br.com.alura.screenmatch.principal;

import java.util.ArrayList;

import br.com.alura.screenmatch.calculo.CalculadoraDeTempo;
import br.com.alura.screenmatch.calculo.FiltroRecomendacao;
import br.com.alura.screenmatch.modelos.Episodio;
import br.com.alura.screenmatch.modelos.Filme;
import br.com.alura.screenmatch.modelos.Serie;

public class Principal {
	
	public static void main(String[] args) {
		
		Filme meuFilme = new Filme("Black Mirror", 2017);						
		meuFilme.setDuracaoEmMinutos(180);	
		System.out.println("Duração do Filme: " + meuFilme.getDuracaoEmMinutos());
					
		meuFilme.avalia(8);
		meuFilme.avalia(5);
		meuFilme.avalia(10);
		
		meuFilme.exibeFichaTecnica();
		
		System.out.println(meuFilme.getTotalDeAvaliacoes());		
		System.out.println(meuFilme.retornaMedia());
		
		Serie lost = new Serie("Lost", 2000);		
		lost.setTemporadas(10);
		lost.setEpisodiosPorTemporada(10);
		lost.setMinutosPorEpisodio(50);
		lost.exibeFichaTecnica();
		System.out.println("Duração para maratonar Lost: " + lost.getDuracaoEmMinutos());
		
		Filme outroFilme = new Filme("Avatar", 2023);				
		outroFilme.setDuracaoEmMinutos(200);
		
		CalculadoraDeTempo calculadora = new CalculadoraDeTempo();
		calculadora.inclui(meuFilme);
		calculadora.inclui(outroFilme);
		calculadora.inclui(lost);
		System.out.println(calculadora.getTempoTotal());
		
		FiltroRecomendacao filtro = new FiltroRecomendacao();
		filtro.filtra(meuFilme);
		
		Episodio episodio = new Episodio();
		episodio.setNumero(1);
		episodio.setSerie(lost);
		episodio.setTotalVisualizacoes(200);
		filtro.filtra(episodio);
		
		var filmeDoJose = new Filme("Dogville", 2003);
		filmeDoJose.setDuracaoEmMinutos(200);
		filmeDoJose.avalia(10);
		
		ArrayList<Filme> listaDeFilmes = new ArrayList<>();
		listaDeFilmes.add(meuFilme);
		listaDeFilmes.add(outroFilme);
		listaDeFilmes.add(filmeDoJose);
		
		System.out.println("Tamanho da lista: " + listaDeFilmes.size());
		System.out.println("Primeiro filme: " + listaDeFilmes.get(0).getNome());
		
		System.out.println("toString do filme: " + listaDeFilmes.get(0).toString());
		System.out.println(listaDeFilmes);
		
		listaDeFilmes.forEach((f -> System.out.println(f.getNome())));
		
	}

}