package br.com.alura.linguagens.api.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "mainLanguages")
public class Linguagem {
	
	@Id
	private String id;
		
 	private String title;
	
	private String image;
			
	private Integer ranking;
	
}