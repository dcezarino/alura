package br.com.alura.spring.data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer> {
	
	List<Funcionario> findByNome(String nome);

	/*
	 * Metodo criado usando JPQL
	 */
	@Query("SELECT f FROM Funcionario f where f.nome = :nome AND f.salario >= :salario AND f.dataContratacao = :dataContratacao")
	List<Funcionario> findByNomeSalarioMaiorDataContratacao(String nome, Double salario, LocalDate dataContratacao);
	
	@Query(value = "SELECT * FROM funcionarios f where f.data_contratacao >= :dataContratacao", nativeQuery = true)
	List<Funcionario> findByDataContratacaoMaior(LocalDate dataContratacao);
	
	/*
	 * Metodo criado para retornar apenas os campos informados de acordo com a projecao criada (FuncionarioProjecao)
	 */
	@Query(value = "select f.id, f.nome, f.salario from funcionarios f", nativeQuery = true)
	List<FuncionarioProjecao> findFuncionarioSalario();

}