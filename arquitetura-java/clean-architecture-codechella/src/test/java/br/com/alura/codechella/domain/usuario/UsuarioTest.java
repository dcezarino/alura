package br.com.alura.codechella.domain.usuario;

import br.com.alura.codechella.domain.entities.usuario.FabricaDeUsuario;
import br.com.alura.codechella.domain.entities.usuario.Usuario;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UsuarioTest {

    @Test
    public void naoDeveCadastrarUsuarioComCpfNoFormatoInvalido() {

        assertThrows(IllegalArgumentException.class,
                () -> new Usuario("12345678999", "Carlos Modelo Testa", LocalDate.parse("1990-09-08"), "usuario@gmail.com"));

        assertThrows(IllegalArgumentException.class,
                () -> new Usuario("123456789-99", "Carlos Modelo Testa", LocalDate.parse("1990-09-08"), "usuario@gmail.com"));

        assertThrows(IllegalArgumentException.class,
                () -> new Usuario("123.456.78999", "Carlos Modelo Testa", LocalDate.parse("1990-09-08"), "usuario@gmail.com"));

        assertThrows(IllegalArgumentException.class,
                () -> new Usuario("123456.789-99", "Carlos Modelo Testa", LocalDate.parse("1990-09-08"), "usuario@gmail.com"));

    }

    @Test
    public void deveCriarUsuarioUsandoFabricaDeUsuario() {

        FabricaDeUsuario fabricaDeUsuario = new FabricaDeUsuario();
        Usuario returnUsuario = fabricaDeUsuario.comNomeCpfNascimento("123.456.789-00", "Usuário Modelo 01", LocalDate.parse("1989-05-20"));

        assertEquals("Usuário Modelo 01", returnUsuario.getNome());
        assertEquals("123.456.789-00", returnUsuario.getCpf());
        assertEquals(LocalDate.parse("1989-05-20"), returnUsuario.getNascimento());

        Usuario returnUsuarioEndereco = fabricaDeUsuario.incluiEndereco("12345-000", 40, "Apto 401");

        assertEquals("12345-000", returnUsuarioEndereco.getEndereco().getCep());
        assertEquals(40, returnUsuarioEndereco.getEndereco().getNumero());
        assertEquals("Apto 401", returnUsuarioEndereco.getEndereco().getComplemento());

    }

    @Test
    public void naoDeveCriarUsuarioQuandoIdadeMenorQueDezoito() {

        // Crio uma data de nascimento que subtrai 17 anos da data atual
        LocalDate dataNascimento = LocalDate.now().minusYears(17);  // Um usuário com 17 anos

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Usuario("123.123.123-22", "Usuário Modelo 02", dataNascimento, null));

        // Confiro se a mensagem da exceção é a mensagem que eu esperava, referente à idade inferior
        assertEquals("Usuário deve ter pelo menos 18 anos de idade!", exception.getMessage());
    }

}