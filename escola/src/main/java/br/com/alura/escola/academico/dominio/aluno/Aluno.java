package br.com.alura.escola.academico.dominio.aluno;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.escola.shared.dominio.CPF;

// Aggregate root
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
	
	public void adicionarTelefone (String ddd, String telefone) throws TelefoneMaxException {
		
		if (telefones.size() == 2) {			
			throw new TelefoneMaxException();
		} 				
			this.telefones.add(new Telefone(ddd, telefone));			
		
	}
	
	public CPF getCpf() {
		return cpf;
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
	
	public String getSenha() {
		return senha;
	}

	@Override
	public String toString() {
		return "Aluno [cpf=" + cpf.getNumero() + ", nome=" + nome + ", email=" + email.getEndereco() + ", telefones=" + telefones.get(0).getDdd() + telefones.get(0).getNumero() +"]";
	}

}