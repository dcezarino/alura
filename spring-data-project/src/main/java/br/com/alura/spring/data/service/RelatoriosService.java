package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {
	
	private Boolean system = true;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public void inicial(Scanner scanner) {

		while (system) {

			System.out.println("Qual acao de relatorio deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Busca Funcionario Nome");
			System.out.println("2 - Busca Funcionario Nome, Salario Maior e Igual e Data Contratacao");
			System.out.println("3 - Busca Funcionario por Data da Contratacao");
			System.out.println("4 - Busca Funcionario Salario");

			int action = scanner.nextInt();

			switch (action) {
			case 1:
				buscaFuncionarioNome(scanner);
				break;
			case 2:
				buscaNomeSalarioMaiorDataContratacao(scanner);
				break;
			case 3:
				buscaFuncionarioDataContratacao(scanner);
				break;
			case 4:
				buscaFuncionarioSalario();
				break;				
			default:
				system = false;
				break;

			}

		}

	}
	
	private void buscaFuncionarioNome(Scanner scanner) {
		
		System.out.println("Qual nome deseja pesquisar");		
		List<Funcionario> funcionarios = funcionarioRepository.findByNome(scanner.next());
		
		funcionarios.forEach(System.out::println);		
		
	}

	
	private void buscaNomeSalarioMaiorDataContratacao(Scanner scanner) {
		
		System.out.println("Qual nome deseja pesquisar");
		String nome = scanner.next();
		
		System.out.println("Qual salario deseja pesquisar");
		Double salario = scanner.nextDouble();
		
		System.out.println("Qual Data Contratacao deseja pesquisar");
		String data = scanner.next();		
		
		List<Funcionario> funcionarios = funcionarioRepository.findByNomeSalarioMaiorDataContratacao(nome, salario, LocalDate.parse(data, formatter));
				
		funcionarios.forEach(System.out::println);		
		
	}
	
	private void buscaFuncionarioDataContratacao(Scanner scanner) {
		
		System.out.println("Qual Data Contratacao deseja pesquisar");
		String data = scanner.next();
		
		List<Funcionario> funcionarios = funcionarioRepository.findByDataContratacaoMaior(LocalDate.parse(data, formatter));
				
		funcionarios.forEach(System.out::println);		
		
	}
	
	private void buscaFuncionarioSalario() {
		
		List<FuncionarioProjecao> funcionarios = funcionarioRepository.findFuncionarioSalario();
		
		funcionarios.forEach(f -> System.out.println("funcionario: id: " + f.getId() + " | nome: " + f.getNome() + " | salario: " + f.getSalario()) );
		
	}

}
