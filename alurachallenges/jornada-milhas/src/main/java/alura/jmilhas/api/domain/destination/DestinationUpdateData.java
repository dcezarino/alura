package alura.jmilhas.api.domain.destination;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DestinationUpdateData(
		
		@NotNull
		Long id,
		String image_url1,
		String image_url2,
		String name,
		String goal,
		String description,
		
		@Positive
		BigDecimal price) {

}
