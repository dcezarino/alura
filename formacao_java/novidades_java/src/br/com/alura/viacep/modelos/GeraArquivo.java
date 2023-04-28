package br.com.alura.viacep.modelos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GeraArquivo {
	
	public void criaArquivo (List<Endereco> enderecos) throws IOException {
		
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();
		
		FileWriter escrita = new FileWriter("enderecos.json");
		escrita.write(gson.toJson(enderecos));
		escrita.close();
		
	}

}
