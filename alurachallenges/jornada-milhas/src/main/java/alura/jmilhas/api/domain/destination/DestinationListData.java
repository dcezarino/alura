package alura.jmilhas.api.domain.destination;

import java.math.BigDecimal;

public record DestinationListData(Long id, String photo, String name, BigDecimal price) {

	public DestinationListData(Destination destination) {
		
		this(destination.getId(), destination.getPhoto(), destination.getName(), destination.getPrice());

	}

}
