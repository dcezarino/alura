package br.com.alura.playbox.principal;

import br.com.alura.playbox.modelos.MinhasPreferidas;
import br.com.alura.playbox.modelos.Musica;
import br.com.alura.playbox.modelos.Podcast;

public class Principal {
	
	public static void main(String[] args) {
		
		Musica musica = new Musica();
		musica.setAlbum("Pop Life");
		musica.setTitulo("Love is Gone");
		musica.setCantor("David Guetta");
		musica.setGenero("Pop");
		
		for (int i = 0; i < 5000; i++) {
			musica.reproduz();
		}
		
		Podcast podcast = new Podcast();
		podcast.setApresentador("Gustavo Apresentador");
		podcast.setTitulo("Tecnologias do mercado");
		
		for (int i = 0; i < 2000; i++) {
			podcast.curte();
		}
		
		MinhasPreferidas minhasPreferidas = new MinhasPreferidas();
		minhasPreferidas.inclui(musica);
		minhasPreferidas.inclui(podcast);
		
	}

}