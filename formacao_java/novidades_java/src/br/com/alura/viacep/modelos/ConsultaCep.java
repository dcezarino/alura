package br.com.alura.viacep.modelos;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;

public class ConsultaCep {
	
	public EnderecoRecord buscaEndereco(String cep) {
		
		URI endereco = URI.create("https://viacep.com.br/ws/" + cep.replace(" ", "+") + "/json/");
		
		HttpRequest request = HttpRequest.newBuilder().uri(endereco).build();
				
		try {
			
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			
			return new Gson().fromJson(response.body(), EnderecoRecord.class);
			
		} catch (Exception e ) {
			
			throw new RuntimeException("Não obteve o endereço a partir do cep informado.");
			
		}

	}

}
