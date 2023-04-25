package br.com.alura.playbox.modelos;

public class MinhasPreferidas {

	public void inclui(Audio audio) {
		
		if (audio.getClassificacao() >= 9 ) {
			
			System.out.println(audio.getTitulo() + " � considerado sucesso nas plataformas digitais.");
			
		} else {
			
			System.out.println(audio.getTitulo() + " � um dos favoritos ouvidos por muitos.");
			
		}

	}

}