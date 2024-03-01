package br.com.alura.refl;

import br.com.alura.Pessoa;

public class PessoaFixture {

    public static Pessoa buildPessoa() {
        return new Pessoa(32, "João","12345");
    }

    public static Pessoa buildPessoaSemCPF() {
        return new Pessoa("João");
    }


}
