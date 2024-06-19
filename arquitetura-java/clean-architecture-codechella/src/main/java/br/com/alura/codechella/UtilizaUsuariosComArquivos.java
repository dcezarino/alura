package br.com.alura.codechella;

import br.com.alura.codechella.application.gateways.RepositorioDeUsuarioEmArquivo;
import br.com.alura.codechella.domain.entities.usuario.Usuario;

import java.time.LocalDate;

public class UtilizaUsuariosComArquivos {

    public static void main(String[] args) {

        RepositorioDeUsuarioEmArquivo repositorioDeUsuarioEmArquivo = new RepositorioDeUsuarioEmArquivo();

        repositorioDeUsuarioEmArquivo.cadastrarUsuario(new Usuario("232.322.232-22", "João Carlos",
                LocalDate.parse("2000-10-15"), "joao@gmail.com"));
        repositorioDeUsuarioEmArquivo.cadastrarUsuario(new Usuario("232.322.232-21", "Maria Carla",
                LocalDate.parse("2000-10-16"), "mariac@gmail.com"));
        repositorioDeUsuarioEmArquivo.cadastrarUsuario(new Usuario("232.322.232-20", "Joana Maria",
                LocalDate.parse("2000-10-17"), "joanam@gmail.com"));
        repositorioDeUsuarioEmArquivo.cadastrarUsuario(new Usuario("232.322.232-19", "Maria Joana",
                LocalDate.parse("2000-10-18"), "mariaj@gmail.com"));

        repositorioDeUsuarioEmArquivo.geraEmArquivo("usuarios.txt");

    }

}
