package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//@WebFilter("/entrada")
public class MonitoramentoFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("MonitoramentoFilter");
		
		/*
		 * Medir tempo de execu��o
		 */
		Long antes = System.currentTimeMillis();
		
		String acao = request.getParameter("acao");	
						
		/*
		 * Para chamar o pr�ximo filtro na cadeia, usamos o objeto FilterChain
		 */
		chain.doFilter(request, response);
		
		Long depois = System.currentTimeMillis();
		System.out.println("Tempo execu��o da a��o " + acao + " -> " + (depois - antes));
		
	}

}
