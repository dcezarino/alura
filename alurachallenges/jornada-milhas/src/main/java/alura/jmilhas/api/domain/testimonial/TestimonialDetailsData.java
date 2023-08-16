package alura.jmilhas.api.domain.testimonial;

public record TestimonialDetailsData(Long id, String image_url1, String description, String actor) {

    public TestimonialDetailsData(Testimonial testimonial) {

        this(testimonial.getId(), testimonial.getImage_url1(), testimonial.getDescription(), testimonial.getActor());

    }

}
