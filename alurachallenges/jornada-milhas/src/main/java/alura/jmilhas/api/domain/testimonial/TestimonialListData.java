package alura.jmilhas.api.domain.testimonial;

public record TestimonialListData(Long id, String image_url1, String description, String actor) {
	
	public TestimonialListData(Testimonial depoimento) {
		
		this(depoimento.getId(), depoimento.getImage_url1(), depoimento.getDescription(), depoimento.getActor());
		
	}

}