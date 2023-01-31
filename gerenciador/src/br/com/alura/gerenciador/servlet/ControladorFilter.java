package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.acao.Acao;

/**
 * Servlet Filter implementation class AutorizacaoFilter
 */
//@WebFilter("/entrada")
public class ControladorFilter extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("ControladorFilter");		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		String paramAcao = request.getParameter("acao");		
		String nomeDaClasse = "br.com.alura.gerenciador.acao." + paramAcao;
		
		// Carrega a classe com o nome da String 
		// Executa o método
		String nome;
		try {
			Class classe = Class.forName(nomeDaClasse);
			// Cria a instancia da acao  
			Object obj = classe.newInstance();
			// Efetua o cast
			Acao acao = (Acao) obj;
			nome = acao.executa(request, response);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ServletException | IOException e) {
			
			throw new ServletException();
			
		}
		
		String[] tipoEEndereco = nome.split(":");
		
		if(tipoEEndereco[0].equals("forward")) {
			
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/" + tipoEEndereco[1]);
			rd.forward(request, response);
			
		} else {
			
			response.sendRedirect(tipoEEndereco[1]);
			
		}
		
	}

}
