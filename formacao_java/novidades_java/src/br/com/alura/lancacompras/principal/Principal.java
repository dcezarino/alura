package br.com.alura.lancacompras.principal;

import java.util.Collections;
import java.util.Scanner;

import br.com.alura.lancacompras.modelos.CartaoDeCredito;
import br.com.alura.lancacompras.modelos.Compra;

public class Principal {
		
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("****************************************************"); 
		System.out.println("****** :: Sistema de Lançamento de Compras :: ******"); 
		System.out.println("****************************************************");
		
		System.out.println("Digite o limite do cartão: ");
		double limiteCartao = sc.nextDouble();		
		CartaoDeCredito cartaoDeCredito = new CartaoDeCredito(limiteCartao);
		
		Integer opcao = 1;
		while (opcao != 0) {
			
			System.out.println("Digite a descrição da compra: ");
			String descricao = sc.next();			
			
			System.out.println("Digite o valor da compra: ");
			double valorProduto = sc.nextDouble();
			
			if(cartaoDeCredito.getSaldo() >= valorProduto) {
				
				Compra compra = new Compra(descricao, valorProduto);
				
				boolean resultLancaCompra = cartaoDeCredito.lancaCompra(compra);
				
				if(resultLancaCompra) {
					
					System.out.println("Compra efetuada!");				
					
					System.out.println("Digite 0 para Sair ou 1 para continuar: ");
					opcao = sc.nextInt();
					
				} 
				
			} else {
				
				System.out.println("Limite indisponível");
				opcao = 0;
				
			}			
			
		}	
		
		System.out.println("***************************************"); 
		System.out.println("****** :: Compras realizadas :: *******"); 
		System.out.println("***************************************");
		
		Collections.sort(cartaoDeCredito.getCompras());
		cartaoDeCredito.getCompras().forEach(( l -> System.out.println("\nProduto: " + l.getDescricao() + "\nValor: " + l.getValor())));
		
		System.out.println("\nSaldo do cartão: " + cartaoDeCredito.getSaldo());
		
		System.out.println("\nOperação finalizada");
		
		System.out.println("***************************************"); 
		System.out.println("***************************************"); 
		System.out.println("***************************************");
		
		sc.close();
		
	}

}