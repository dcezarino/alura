package br.com.alura.autofipe.service;

import java.util.List;

public interface IConverteDados {

    // Generics (qualquer coisa)
    <T> T obterDados(String json, Class<T> classe);

    <T> List<T> obterLista(String json, Class<T> classe);

}
