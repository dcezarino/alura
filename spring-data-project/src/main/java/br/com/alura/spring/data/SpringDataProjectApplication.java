package br.com.alura.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.alura.spring.data.service.CrudCargoService;
import br.com.alura.spring.data.service.CrudFuncionarioService;
import br.com.alura.spring.data.service.CrudUnidadeTrabalhoService;
import br.com.alura.spring.data.service.RelatoriosService;

@EnableJpaRepositories
@SpringBootApplication
public class SpringDataProjectApplication implements CommandLineRunner {

	private Boolean system = true;

	private final CrudCargoService cargoService;

	private final CrudFuncionarioService funcionarioService;

	private final CrudUnidadeTrabalhoService unidadeTrabalhoService;
	
	private final RelatoriosService relatoriosService;

	public SpringDataProjectApplication(CrudCargoService cargoService, CrudFuncionarioService funcionarioService,
			CrudUnidadeTrabalhoService unidadeTrabalhoService, RelatoriosService relatoriosService) {
		this.cargoService = cargoService;
		this.funcionarioService = funcionarioService;
		this.unidadeTrabalhoService = unidadeTrabalhoService;
		this.relatoriosService = relatoriosService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (system) {
			
			System.out.println("Qual função deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Funcionario");
			System.out.println("2 - Cargo");
			System.out.println("3 - Unidade");
			System.out.println("4 - Relatorios");

			Integer function = scanner.nextInt();

			switch (function) {
			case 1:
				funcionarioService.inicial(scanner);
				break;
			case 2:
				cargoService.inicial(scanner);
				break;
			case 3:
				unidadeTrabalhoService.inicial(scanner);
				break;
			case 4:
				relatoriosService.inicial(scanner);
				break;
			default:
				System.out.println("Finalizando");
				system = false;
				break;
				
			}
		}
	}
}
