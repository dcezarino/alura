package alura.jmilhas.api.domain.destination;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "destinations")
@Entity(name = "Destination")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Destination {
	
	public Destination(DestinationData destinationDataRequest) {
		this.image_url1 = destinationDataRequest.image_url1();
		this.image_url2 = destinationDataRequest.image_url2();
		this.name = destinationDataRequest.name();
		this.goal = destinationDataRequest.goal();
		this.description = destinationDataRequest.description();
		this.price = destinationDataRequest.price();
		this.active = true;
	}

	public void informationUpdate(@Valid DestinationUpdateData destinationUpdateData) {
		
		if(destinationUpdateData.image_url1() != null) {
			this.image_url1 = destinationUpdateData.image_url1();
		}

		if(destinationUpdateData.image_url2() != null) {
			this.image_url2 = destinationUpdateData.image_url2();
		}

		if(destinationUpdateData.name() != null) {
			this.name = destinationUpdateData.name();
		}

		if(destinationUpdateData.goal() != null) {
			this.goal = destinationUpdateData.goal();
		}

		if(destinationUpdateData.description() != null) {
			this.description = destinationUpdateData.description();
		}

		if(destinationUpdateData.price() != null && destinationUpdateData.price().compareTo(BigDecimal.ZERO) > 0) {
			this.name = destinationUpdateData.name();
		}
		
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String image_url1;

	private String image_url2;
	
	private String name;

	private String goal;

	private String description;
	
	private BigDecimal price;
	
	private boolean active;

	public void delete() {
		this.active = false;
	}

}