package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Usuario;

public class Login implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");

		System.out.println("Logando " + login);
		
		/*
		 * Busca no banco se as credenciais do usuário estão corretas e retorna o usuário		 
		 */
		Banco banco = new Banco();
		Usuario usuario = banco.existeUsuario(login, senha);
		
		/*
		 * Se retornar o usuário, então permite o acesso a Lista de Empresas
		 */
		if(usuario != null) {
			
			System.out.println("Usuário Existe");
			HttpSession sessao = request.getSession();
			sessao.setAttribute("usuarioLogado", usuario);			
			return "redirect:entrada?acao=ListaEmpresas";
			
		} else {
			
			return "redirect:entrada?acao=LoginForm";
			
		}
				
	}

}
