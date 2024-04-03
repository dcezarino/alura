package br.com.alura.viacep.principal;

import br.com.alura.viacep.modelos.Endereco;

import java.io.IOException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner leitura = new Scanner(System.in);
        System.out.println("Informe o cep para efetuar a busca:");

        var cep = leitura.nextLine();

        try {
            Endereco newEndereco = new ConsultaCep().buscaEndereco(cep);
            System.out.println(newEndereco);

            new GeradorDeArquivo().salvaJson(newEndereco);

        } catch (RuntimeException | IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Finalizando a aplicação;");
        }

    }

}