package br.com.alura.linguagens.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.linguagens.api.entities.Linguagem;
import br.com.alura.linguagens.api.services.LinguagemService;

@RestController
@RequestMapping("/linguagem")
public class LinguagemController {
	
	@Autowired
	private LinguagemService linguagemService;
	
	@GetMapping
	public ResponseEntity<List<Linguagem>> getAll(){
		
		return new ResponseEntity<>(linguagemService.getAll(), HttpStatus.OK);
		
	}	
	
	@PostMapping
	public ResponseEntity<Linguagem> postLinguagem(@RequestBody Linguagem linguagem) {
		
		return new ResponseEntity<>(linguagemService.create(linguagem), HttpStatus.CREATED);	
		
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Linguagem> putLinguagem(@PathVariable String id,  @RequestBody Linguagem language) {
		
		return new ResponseEntity<>(linguagemService.update(id, language), HttpStatus.OK);
		
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteLinguagem(@PathVariable String id) {		
		
		this.linguagemService.delete(id);
		
	}	

}