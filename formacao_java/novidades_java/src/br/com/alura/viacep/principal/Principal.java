package br.com.alura.viacep.principal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.alura.viacep.modelos.ConsultaCep;
import br.com.alura.viacep.modelos.Endereco;
import br.com.alura.viacep.modelos.EnderecoRecord;
import br.com.alura.viacep.modelos.GeraArquivo;

public class Principal {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		Scanner sc = new Scanner(System.in);
		String busca = "";
		List<Endereco> enderecos = new ArrayList<>();
		
		while (!busca.equalsIgnoreCase("sair")) {
			
			System.out.println("Digite o cep para efetuar a consulta: ");
			busca = sc.nextLine();
			
			if (busca.equalsIgnoreCase("sair")) {
				break;
			}
			
			try {
				
				ConsultaCep consultaCep = new ConsultaCep();
				EnderecoRecord endereco = consultaCep.buscaEndereco(busca);
				System.out.println(endereco);
				
				Endereco meuEndereco = new Endereco(endereco);
				System.out.println("Título já convertido: ");
				System.out.println(meuEndereco);
				
				enderecos.add(meuEndereco);
				
				GeraArquivo geraArquivo = new GeraArquivo();
				geraArquivo.criaArquivo(enderecos);
				
			} catch (RuntimeException | IOException e) {
				
				System.out.println(e.getMessage());
				System.out.println("Finalizando a aplicação.");
				busca = "sair";
				
			}
			
		}
		
		System.out.println("O programa finalizou corretamente.");

		sc.close();
		
	}
	
}