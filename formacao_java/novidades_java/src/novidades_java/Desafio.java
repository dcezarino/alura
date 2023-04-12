package novidades_java;

import java.util.Scanner;

public class Desafio {

	public static void main(String[] args) {
		
		String nome = "Cliente Modelo";
		String tipoConta = "Conta Corrente";
		double saldo = 1599.99;
		String menu;
		int opcao = 0;
		double valor = 0;
		
		System.out.println("************************************************");
		System.out.println("\nNome do Cliente: " + nome);
		System.out.println("Tipo Conta: " + tipoConta);
		System.out.println("Saldo Atual: " + saldo);
		System.out.println("\n************************************************");

		menu = """
				**Digite sua Opção**
				1 - Consultar Saldo
				2 - Transferir Valor
				3 - Receber Valor
				4 - Sair
				  """;
		
		Scanner sc = new Scanner(System.in);

		while (opcao != 4) {
			
			System.out.println(menu);
			opcao = sc.nextInt();

			switch (opcao) {
			case 1:
				System.out.println("Saldo atual: " + saldo);							
				break;
			case 2:		
				System.out.println("Informe o valor a ser transferido: ");
				valor = sc.nextDouble();
				if(saldo < valor) {
					System.out.println("Saldo indisponível para transferência " + saldo);
				} else {
					saldo -= valor;
					System.out.println("Saldo atual: " + saldo);
				}
				break;
			case 3:
				System.out.println("Informe o valor a ser depositado: ");
				valor = sc.nextDouble();
				saldo += valor;
				System.out.println("Saldo atual: " + saldo);				
				break;
			default:
				System.out.println("Opção inválida.");
				break;
			}

		}

		sc.close();

	}
	
}
