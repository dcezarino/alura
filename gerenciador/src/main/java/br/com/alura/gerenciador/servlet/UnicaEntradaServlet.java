package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.acao.AlteraEmpresa;
import br.com.alura.gerenciador.acao.ListaEmpresas;
import br.com.alura.gerenciador.acao.MostraEmpresa;
import br.com.alura.gerenciador.acao.NovaEmpresa;
import br.com.alura.gerenciador.acao.RemoveEmpresa;

@WebServlet("/entrada")
public class UnicaEntradaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * Método responsável por centralizar as requisições de entrada e delegar a execução de acordo com a ação passada como parâmetro.
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String paramAcao = request.getParameter("acao");
		
		// Criada classe ListaEmpresas, mas não é um Servlet.
		if (paramAcao.equals("ListaEmpresas")) {
			
			ListaEmpresas acao = new ListaEmpresas();
			
			acao.executa(request, response);
			
		}
		
		// Criada classe MostraEmpresa, mas não é um Servlet.
		if (paramAcao.equals("MostraEmpresa")) {
			
			MostraEmpresa acao = new MostraEmpresa();
			
			acao.executa(request, response);
			
		}
		
		// Criada classe RemoveEmpresa, mas não é um Servlet.
		if (paramAcao.equals("RemoveEmpresa")) {
			
			RemoveEmpresa acao = new RemoveEmpresa();
			
			acao.executa(request, response);
			
		}
		
		// Criada classe AlteraEmpresa, mas não é um Servlet.
		if (paramAcao.equals("AlteraEmpresa")) {
			
			AlteraEmpresa acao = new AlteraEmpresa();
			
			acao.executa(request, response);
			
		}
		
		// Criada classe NovaEmpresa, mas não é um Servlet.
		if (paramAcao.equals("NovaEmpresa")) {
			
			NovaEmpresa acao = new NovaEmpresa();
			
			acao.executa(request, response);
			
		}
		
		
	}

}
