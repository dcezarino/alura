package alura.jmilhas.api.domain.destination;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record DestinationData(
		
		@NotBlank
		String image_url1,

		@NotBlank
		String image_url2,

		@NotBlank
		String name,

		@Size(max = 160, message = "The goal description must have a maximum {max} characters")
		String goal,

		@Size(max = 255, message = "The description must have a maximum {max} characters")
		String description,
		
		@Positive
		BigDecimal price) {

}
