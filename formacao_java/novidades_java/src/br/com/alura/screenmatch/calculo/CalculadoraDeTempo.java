package br.com.alura.screenmatch.calculo;

import br.com.alura.screenmatch.modelos.Titulo;

public class CalculadoraDeTempo {
	
	private int tempoTotal;

	public int getTempoTotal() {
		return tempoTotal;
	}
	
//	public void inclui(Filme f) {
//		tempoTotal += f.getDuracaoEmMinutos();
//	}
//	
//	public void inclui(Serie s) {
//		tempoTotal += s.getDuracaoEmMinutos();
//	}
	
	// Titulo: é a superclasse de filme e serie
	// Filme e Serie extends de Titulo
	public void inclui(Titulo titulo) {
		tempoTotal += titulo.getDuracaoEmMinutos();
	}

}
