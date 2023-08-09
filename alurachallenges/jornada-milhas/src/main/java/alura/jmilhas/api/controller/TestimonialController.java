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

import alura.jmilhas.api.domain.testimonial.TestimonialUpdateData;
import alura.jmilhas.api.domain.testimonial.TestimonialRegistrationData;
import alura.jmilhas.api.domain.testimonial.TestimonialDetailsData;
import alura.jmilhas.api.domain.testimonial.MedicalDetailsData;
import alura.jmilhas.api.domain.testimonial.TestimonialListData;
import alura.jmilhas.api.domain.testimonial.Testimonial;
import alura.jmilhas.api.repository.TestimonialRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("testimonials")
public class TestimonialController {
		
    @Autowired
    private TestimonialRepository testimonialRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid TestimonialRegistrationData testimonialData, UriComponentsBuilder uriBuilder) {
        
    	var testimonial = new Testimonial(testimonialData);
        testimonialRepository.save(testimonial);

        var uri = uriBuilder.path("/testimonials/{id}").buildAndExpand(testimonial.getId()).toUri();

        return ResponseEntity.created(uri).body(new TestimonialDetailsData(testimonial));

    }	

    @GetMapping
    public ResponseEntity<Page<?>> get(@PageableDefault(size = 10, sort = {"actor"}) Pageable pagination) {
    	
        var page = testimonialRepository.findAllByActiveTrue(pagination).map(TestimonialListData::new);
        
        return ResponseEntity.ok(page);    	

    }

    @GetMapping("/testimonials-home")
    public ResponseEntity<?> listRandonTestimonials() {
    	
        var testimonials = testimonialRepository.listRandomTestimonials().stream().map(TestimonialListData::new);
        
        return ResponseEntity.ok(testimonials);    	

    }
    
    @PutMapping
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid TestimonialUpdateData testimonialUpdateData) {

        var testimonial = testimonialRepository.getReferenceById(testimonialUpdateData.id());
        testimonial.updateInformation(testimonialUpdateData);

        return ResponseEntity.ok(new MedicalDetailsData(testimonial));

    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {

        var testimonial = testimonialRepository.getReferenceById(id);
        testimonial.remove();

        return ResponseEntity.noContent().build();

    }
    
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<?> getById(@PathVariable Long id) {

        var testimonial = testimonialRepository.getReferenceById(id);
        
        return ResponseEntity.ok(new TestimonialDetailsData(testimonial));

    }
        
}