package br.com.alura.screanmatch.service;

public interface IConverteDados {

    // Generics (qualquer coisa)
    <T> T obterDados(String json, Class<T> classe);

}
