package alura.jmilhas.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import alura.jmilhas.api.domain.depoimento.Depoimento;

@Repository
public interface DepoimentoRepository extends JpaRepository<Depoimento, Long>{

}