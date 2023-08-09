package alura.jmilhas.api.domain.destination;

import java.math.BigDecimal;

public record DestinationDataByName(String image_url1, String image_url2, String name, String goal, String description, BigDecimal price) {
	
	public DestinationDataByName(Destination destination) {
		
		this(destination.getImage_url1(), destination.getImage_url2(), destination.getName(), destination.getGoal(), destination.getDescription(), destination.getPrice());
		
	}

}