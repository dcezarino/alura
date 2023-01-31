package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

@WebServlet("/empresas")
public class ListaEmpresaService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Empresa> listEmpresas = new Banco().getEmpresas();
		
		// Ler cabeçalho da requisição
		String valor = request.getHeader("Accept");
		
		System.out.println(valor);
		
		if (valor.contains("xml")) {
			
			XStream xstream = new XStream();
			xstream.alias("empresa", Empresa.class);
			String xml = xstream.toXML(listEmpresas);
							
			response.setContentType("application/xml");
			response.getWriter().print(xml);
			
		} else if (valor.endsWith("json"))  {
			
			// Biblioteca do Google (GSON)
			Gson gson = new Gson();
			String json = gson.toJson(listEmpresas);
					
			response.setContentType("application/json");
			response.getWriter().print(json);
			
		} else {
			
			response.setContentType("application/json");
			response.getWriter().print("{'message:':'no content'}");
			
		}

	}

}
