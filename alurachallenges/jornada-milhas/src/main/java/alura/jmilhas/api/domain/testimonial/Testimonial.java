package alura.jmilhas.api.domain.testimonial;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "testimonial")
@Entity(name = "Testimonial")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Testimonial {
	
	public Testimonial(TestimonialRegistrationData request) {
		
		this.image_url1 = request.image_url1();
		this.description = request.description();
		this.actor = request.actor();
		this.active = true;
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String image_url1;
	
	private String description;
	
	@Column(name = "actor")
	private String actor;
	
    private boolean active;

	public void updateInformation(TestimonialUpdateData dados) {
		
		if (dados.image_url1() != null) {
			this.image_url1 = dados.image_url1();
		} 

		if (dados.description() != null) {
			this.description = dados.description();
		} 
		
		if (dados.actor() != null) {
			this.actor = dados.actor();
		} 
		
	}

	public void remove() {
		
		this.active = false;
		
	}

}