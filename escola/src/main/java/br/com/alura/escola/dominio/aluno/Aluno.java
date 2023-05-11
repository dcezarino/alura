package br.com.alura.escola.dominio.aluno;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
	
	private CPF cpf;
	private String nome;
	private Email email;
	private List<Telefone> telefones = new ArrayList<>();
	private String senha;
	
	public Aluno(CPF cpf, String nome, Email email) {
		
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		
	}
	
	public void adicionarTelefone (String ddd, String telefone) {		
		this.telefones.add(new Telefone(ddd, telefone));
	}
	
	public String getCpf() {
		return cpf.getNumero();
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email.getEndereco();
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}
	
	@Override
	public String toString() {
		return "Aluno [cpf=" + cpf.getNumero() + ", nome=" + nome + ", email=" + email.getEndereco() + ", telefones=" + telefones.get(0).getDdd() + telefones.get(0).getNumero() +"]";
	}

}