package br.com.alura.codechella.config;

import br.com.alura.codechella.application.gateways.RepositorioDeUsuario;
import br.com.alura.codechella.application.usecases.CriarUsuario;
import br.com.alura.codechella.application.usecases.ListarUsuarios;
import br.com.alura.codechella.infra.gateways.UsuarioEntityMapper;
import br.com.alura.codechella.infra.gateways.UsuarioRepositoryJpa;
import br.com.alura.codechella.infra.persistency.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioConfig {

    @Bean
    CriarUsuario criarUsuario (RepositorioDeUsuario repositorioDeUsuario) {
        return new CriarUsuario(repositorioDeUsuario);
    }

    @Bean
    UsuarioRepositoryJpa criarRepositorioJPA (UsuarioRepository repositorio, UsuarioEntityMapper mapper) {
        return new UsuarioRepositoryJpa(repositorio, mapper);
    }

    @Bean
    ListarUsuarios listUsers (RepositorioDeUsuario repositorioDeUsuario) {
        return new ListarUsuarios(repositorioDeUsuario);
    }


    @Bean
    UsuarioEntityMapper returnMapper () {
        return new UsuarioEntityMapper();
    }


}
