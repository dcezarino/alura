package br.com.alura.gerenciador.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Banco {

	// Atributo estático da classe (static)
	private static List<Empresa> lista = new ArrayList<>();
	private static Integer chaveSequencial = 1;

	// Bloco que adiciona 2 empresas na lista para efetuar a consulta através da
	// Servlet utilizando o método doGet
	static {

		Empresa empresa = new Empresa();
		empresa.setId(chaveSequencial++);
		empresa.setNome("Alura");

		Empresa newEmpresa = new Empresa();
		newEmpresa.setId(chaveSequencial++);
		newEmpresa.setNome("Caelum");
		
		lista.add(empresa);
		lista.add(newEmpresa);
		
	}

	public void adiciona(Empresa empresa) {

		empresa.setId(Banco.chaveSequencial++);
		Banco.lista.add(empresa);

	}

	public List<Empresa> getEmpresas() {

		return Banco.lista;

	}

	public void removeEmpresa(Integer id) {
		
		Iterator<Empresa> it = lista.iterator();
		
		while(it.hasNext()) {
			
			Empresa emp = it.next();
			
			if (emp.getId() == id) {
				it.remove();
			}
			
		}

	}

	public Empresa buscaEmpresaById(Integer id) {
		
		for (Empresa empresa : lista) {
			
			if(empresa.getId() == id)
				
				return empresa;
			
		}
		
		return null;		

	}

}
