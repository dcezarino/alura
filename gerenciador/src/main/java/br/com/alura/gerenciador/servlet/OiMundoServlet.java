package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/oi")
public class OiMundoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {			
		
		PrintWriter out = resp.getWriter();
		// A classe out.println imprime no navegador como resposta
		out.println("<html>");
		out.println("<body>");
		out.println("oi, mundo, apresentando o primeiro proheto servlet criado.");
		out.println("<body>");		
		out.println("</body>");		
		out.println("</html>");		
		
	}

}
