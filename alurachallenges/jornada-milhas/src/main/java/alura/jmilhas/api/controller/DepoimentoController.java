package alura.jmilhas.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import alura.jmilhas.api.domain.depoimento.*;
import alura.jmilhas.api.repository.DepoimentoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("depoimentos")
public class DepoimentoController {
		
    @Autowired
    private DepoimentoRepository depoimentoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid DadosCadastroDepoimento dadosCadastroDepoimento, UriComponentsBuilder uriBuilder) {
        
    	var depoimento = new Depoimento(dadosCadastroDepoimento);
        depoimentoRepository.save(depoimento);

        var uri = uriBuilder.path("/depoimentos/{id}").buildAndExpand(depoimento.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoDepoimento(depoimento));

    }	

    @GetMapping
    public ResponseEntity<Page<?>> get(@PageableDefault(size = 10, sort = {"nomeResponsavel"}) Pageable paginacao) {
    	
        var page = depoimentoRepository.findAll(paginacao).map(DadosListagemDepoimento::new);
        
        return ResponseEntity.ok(page);    	

    }
    
    @PutMapping
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid DadosAtualizacaoDepoimento dadosAtualizacaoDepoimento) {

        var depoimento = depoimentoRepository.getReferenceById(dadosAtualizacaoDepoimento.id());
        depoimento.atualizarInformacoes(dadosAtualizacaoDepoimento);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(depoimento));

    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {

        var depoimento = depoimentoRepository.getReferenceById(id);
        depoimento.excluir();

        return ResponseEntity.noContent().build();

    }
    
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<?> getById(@PathVariable Long id) {

        var depoimento = depoimentoRepository.getReferenceById(id);
        
        return ResponseEntity.ok(new DadosDetalhamentoDepoimento(depoimento));

    }
        
}