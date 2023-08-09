package alura.jmilhas.api.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import alura.jmilhas.api.domain.destination.Destination;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long>{

	Page<Destination> findAllByActiveTrue(Pageable paginacao);

	Destination findDestinationByName(String name);

}