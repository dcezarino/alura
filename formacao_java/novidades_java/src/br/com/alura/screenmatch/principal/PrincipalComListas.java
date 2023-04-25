package br.com.alura.screenmatch.principal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import br.com.alura.screenmatch.modelos.Filme;
import br.com.alura.screenmatch.modelos.Serie;
import br.com.alura.screenmatch.modelos.Titulo;

public class PrincipalComListas {

	public static void main(String[] args) {
		
		
		Filme meuFilme = new Filme("Black Mirror", 2017);	
		meuFilme.avalia(9);
		
		Filme outroFilme = new Filme("Avatar", 2023);
		outroFilme.avalia(6);
		
		var filmeDoJose = new Filme("Dogville", 2003);
		filmeDoJose.avalia(10);
		
		Serie lost = new Serie("Lost", 2000);
		
		ArrayList<Titulo> lista = new ArrayList<>();
		lista.add(meuFilme);
		lista.add(outroFilme);
		lista.add(filmeDoJose);
		lista.add(lost);

		// Invocando o toString da classe Filme
		for (Titulo item : lista) {
			
			System.out.println(item.getNome());
			
			// Java 17
			if(item instanceof Filme filme && filme.getClassificacao() > 2) {
				System.out.println("Classificação " + filme.getClassificacao());				
			}
			
		}
		
		/* 
		 * A partir do Java 8, foi adicionado na interface List, a qual a classe ArrayList implementa,			 
		 * um novo método chamado forEach
		 */
		lista.forEach((l -> System.out.println(l)));
		
		/* Method Reference, que nada mais é do que uma forma reduzida de uma expressão lambda */
		lista.forEach(System.out::println);
		
		ArrayList<String> buscaPorArtista = new ArrayList<>();
		buscaPorArtista.add("Adam Sandler");
		buscaPorArtista.add("Paulo");
		buscaPorArtista.add("Jacqueline");
		
		System.out.println(buscaPorArtista);
		
		System.out.println("Depois da ordenação");
		Collections.sort(buscaPorArtista);
		
		System.out.println(buscaPorArtista);

		System.out.println("Lista de Títulos ordenados");
		Collections.sort(lista);
		System.out.println(lista);
		
		// Ordenando a lista pelo ano de lançamento
		System.out.println("Lista de Títulos ordenados pelo ano de lançamento");
		lista.sort(Comparator.comparing(Titulo::getAnoDeLancamento));		
		System.out.println(lista);
		
	}

}
