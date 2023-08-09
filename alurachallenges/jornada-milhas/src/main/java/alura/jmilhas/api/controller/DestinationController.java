package alura.jmilhas.api.controller;

import alura.jmilhas.api.domain.destination.*;
import alura.jmilhas.api.infra.integration.ChatGptIntegrationService;
import alura.jmilhas.api.repository.DestinationRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("destinations")
public class DestinationController {

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private ChatGptIntegrationService chatGptIntegrationService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid DestinationData destinationData, UriComponentsBuilder uriBuilder) {

        if(destinationData.description() == null || destinationData.description().isEmpty()) {

            String text = chatGptIntegrationService.destinationText(destinationData.name()).trim();
            DestinationData newData = new DestinationData(
                    destinationData.image_url1(),
                    destinationData.image_url2(),
                    destinationData.name(),
                    destinationData.goal(),
                    text,
                    destinationData.price()
            );

            var destination = new Destination(newData);

            destinationRepository.save(destination);

            var uri = uriBuilder.path("/destinations/{id}").buildAndExpand(destination.getId()).toUri();

            return ResponseEntity.created(uri).body(new DestinationDetailsData(destination));
        }

        var destination = new Destination(destinationData);

        destinationRepository.save(destination);

        var uri = uriBuilder.path("/destinations/{id}").buildAndExpand(destination.getId()).toUri();

        return ResponseEntity.created(uri).body(new DestinationDetailsData(destination));

    }
    
    @GetMapping
    @Transactional
    public ResponseEntity<Page<?>> get(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
    	
        var page = destinationRepository.findAllByActiveTrue(pagination).map(DestinationListData::new);
        
        return ResponseEntity.ok(page);    	

    }
    
    @PutMapping
    @Transactional
    public ResponseEntity<?> updateById(@RequestBody @Valid DestinationUpdateData destinationUpdateData) {

        var destination = destinationRepository.getReferenceById(destinationUpdateData.id());
        destination.informationUpdate(destinationUpdateData);

        return ResponseEntity.ok(new DestinationDetailsData(destination));

    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteById(@PathVariable Long id) {

        var destination = destinationRepository.getReferenceById(id);
        destination.delete();

        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<?> getById(@PathVariable Long id) {

        var destination = destinationRepository.getReferenceById(id);

        return ResponseEntity.ok(new DestinationDetailsData(destination));

    }

    @GetMapping("findByName/{name}")
    @Transactional
    public ResponseEntity<?> getByName(@PathVariable(value = "name") String name) {

    	var destination = destinationRepository.findDestinationByName(name);

        if(destination != null) {
        	return ResponseEntity.ok().body(new DestinationDataByName(destination));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Destination " + name + " not found.");

    }

}