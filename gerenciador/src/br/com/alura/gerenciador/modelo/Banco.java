package br.com.alura.gerenciador.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Banco {

	// Atributo estático da classe (static)
	private static List<Empresa> lista = new ArrayList<>();
	private static List<Usuario> listaUsuarios = new ArrayList<>();
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

		Usuario u1 = new Usuario();
		u1.setLogin("modelo");
		u1.setSenha("123");

		Usuario u2 = new Usuario();
		u2.setLogin("modelo1");
		u2.setSenha("123");

		listaUsuarios.add(u1);
		listaUsuarios.add(u2);

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

		while (it.hasNext()) {

			Empresa emp = it.next();

			if (emp.getId() == id) {
				it.remove();
			}

		}

	}

	public Empresa buscaEmpresaById(Integer id) {

		for (Empresa empresa : lista) {

			if (empresa.getId() == id)

				return empresa;

		}

		return null;

	}

	public Usuario existeUsuario(String login, String senha) {

		for (Usuario usuario : listaUsuarios) {

			if (usuario.ehIgual(login, senha)) {
				return usuario;
			}
		}

		return null;

	}

}
