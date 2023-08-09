package alura.jmilhas.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import alura.jmilhas.api.domain.testimonial.Testimonial;


@Repository
public interface TestimonialRepository extends JpaRepository<Testimonial, Long> {
	
    Page<Testimonial> findAllByActiveTrue(Pageable pagination);

    @Query("select d from Testimonial d where d.active = true order by function('rand') limit 3")
	List<Testimonial> listRandomTestimonials();

}