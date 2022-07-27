package br.com.alura.linguagens.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.alura.linguagens.api.entities.Linguagem;
import br.com.alura.linguagens.api.exceptions.LinguagemNotFound;
import br.com.alura.linguagens.api.repositories.LinguagemRepository;

@Service
public class LinguagemService {
	
	@Autowired
	private LinguagemRepository linguagemRepository;
	
	public List<Linguagem> getAll() {
		
		return this.linguagemRepository.findByOrderByRankingAsc();
		
	}
	
	public Linguagem create(Linguagem linguagem) {
		
		return this.linguagemRepository.save(linguagem);
		
	}
	
	public Linguagem update(@PathVariable String id,  @RequestBody Linguagem linguagem) {
		
		Linguagem linguagemFound = this.linguagemRepository.findById(id).orElseThrow(() -> new LinguagemNotFound());	
		
		linguagemFound.setTitle(linguagem.getTitle());
		linguagemFound.setImage(linguagem.getImage());
		linguagemFound.setRanking(linguagem.getRanking());
		
		Linguagem linguagemSaved = this.linguagemRepository.save(linguagemFound);
		
		return linguagemSaved;
	}
	
	public void delete(@PathVariable String id) {
		
		Optional<Linguagem> linguagemFound = this.linguagemRepository.findById(id);
		
		this.linguagemRepository.deleteById(linguagemFound.get().getId());		
	
	}	

}
