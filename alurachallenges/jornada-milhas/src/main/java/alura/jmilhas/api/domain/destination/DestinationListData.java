package alura.jmilhas.api.domain.destination;

import java.math.BigDecimal;

public record DestinationListData(Long id, String image_url1, String image_url2, String name, String goal, String description, BigDecimal price) {

	public DestinationListData(Destination destination) {
		
		this(destination.getId(), destination.getImage_url1(), destination.getImage_url2(), destination.getName(), destination.getGoal(), destination.getDescription(), destination.getPrice());

	}

}
