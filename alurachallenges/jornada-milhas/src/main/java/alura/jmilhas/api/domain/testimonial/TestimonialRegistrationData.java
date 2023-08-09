package alura.jmilhas.api.domain.testimonial;

import jakarta.validation.constraints.NotBlank;

public record TestimonialRegistrationData(
		
        @NotBlank
        String image_url1,

		@NotBlank
        String description,
        
        @NotBlank
        String actor) {

}