package br.com.alura.linguagens.api.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.linguagens.api.entities.Linguagem;

@Repository
public interface LinguagemRepository extends MongoRepository<Linguagem, String>{
	
	// Busca as linguagens de programação cadastradas ordenadas pelo ranking
	List<Linguagem> findByOrderByRankingAsc();

}
