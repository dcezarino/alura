package br.com.alura.refl;

import br.com.alura.Pessoa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ObjectToJsonTest {

    @Test
    public void shouldReturnObjectToJson () {

        Pessoa pessoa = PessoaFixture.buildPessoa();
        ObjectToJson objectToJson = new ObjectToJson();
        String atual = objectToJson.transform(pessoa);

        assertInstanceOf(String.class, atual);

    }

}