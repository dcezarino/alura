package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/empresas")
public class ListaEmpresaService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Empresa> listEmpresas = new Banco().getEmpresas();
		
		Gson gson = new Gson();
		String json = gson.toJson(listEmpresas);
				
		response.setContentType("application/json");
		response.getWriter().print(json);
		

	}

}
