package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Não será mais utilizado, pois foi delegado para a classe do pacote acao.
 */

//@WebServlet("/listaEmpresas")
public class ListaEmpresasServlet extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Banco banco = new Banco();
		List<Empresa> listaEmpresas = banco.getEmpresas();
		
		request.setAttribute("empresas", listaEmpresas);
		
		RequestDispatcher rd = request.getRequestDispatcher("/listaEmpresas.jsp");
		rd.forward(request, response);
		
		
	}

}
