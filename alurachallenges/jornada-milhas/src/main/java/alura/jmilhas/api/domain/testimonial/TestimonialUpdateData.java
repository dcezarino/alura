package alura.jmilhas.api.domain.testimonial;

import jakarta.validation.constraints.NotNull;

public record TestimonialUpdateData(

		@NotNull
		Long id,
        String image_url1,
		String description,
        String actor) {

}
