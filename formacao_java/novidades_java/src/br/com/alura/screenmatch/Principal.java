package br.com.alura.screenmatch;

import br.com.alura.screenmatch.calculo.CalculadoraDeTempo;
import br.com.alura.screenmatch.modelos.Filme;
import br.com.alura.screenmatch.modelos.Serie;

public class Principal {
	
	public static void main(String[] args) {
		
		Filme meuFilme = new Filme();
		meuFilme.setNome("Black Mirror");
		meuFilme.setAnoDeLancamento(2017);				
		meuFilme.setDuracaoEmMinutos(180);	
		System.out.println("Duração do Filme: " + meuFilme.getDuracaoEmMinutos());
					
		meuFilme.avalia(8);
		meuFilme.avalia(5);
		meuFilme.avalia(10);
		
		meuFilme.exibeFichaTecnica();
		
		System.out.println(meuFilme.getTotalDeAvaliacoes());		
		System.out.println(meuFilme.retornaMedia());
		
		Serie lost = new Serie();
		lost.setNome("Lost");
		lost.setAnoDeLancamento(2000);
		lost.setTemporadas(10);
		lost.setEpisodiosPorTemporada(10);
		lost.setMinutosPorEpisodio(50);
		lost.exibeFichaTecnica();
		System.out.println("Duração para maratonar Lost: " + lost.getDuracaoEmMinutos());
		
		Filme outroFilme = new Filme();
		outroFilme.setNome("Avatar");
		outroFilme.setAnoDeLancamento(2023);				
		outroFilme.setDuracaoEmMinutos(200);
		
		CalculadoraDeTempo calculadora = new CalculadoraDeTempo();
		calculadora.inclui(meuFilme);
		calculadora.inclui(outroFilme);
		calculadora.inclui(lost);
		System.out.println(calculadora.getTempoTotal());

	}

}