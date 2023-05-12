package br.com.alura.escola.gamificacao.infra.selo;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.escola.gamificacao.dominio.selo.RepositorioDeSelos;
import br.com.alura.escola.gamificacao.dominio.selo.Selo;
import br.com.alura.escola.shared.dominio.CPF;

public class RepositorioDeSelosEmMemoria implements RepositorioDeSelos {
	
	private List<Selo> selos = new ArrayList<>();

	@Override
	public void adicionar(Selo selo) {
		
		this.selos.add(selo);

	}

	@Override
	public List<Selo> selosDoAlunoDeCPF(CPF cpf) {
		
		return null;
		
//		return this.selos
//				   .stream()
//				   .filter(s -> s.getCpfDoAluno().equals(cpf.getNumero()))
//				   .collect(Collections.)
				   
				  

		
	}

}
