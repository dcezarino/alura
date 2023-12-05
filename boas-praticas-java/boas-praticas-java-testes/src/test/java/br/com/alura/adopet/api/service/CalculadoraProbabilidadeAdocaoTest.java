package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class CalculadoraProbabilidadeAdocaoTest {

    @InjectMocks
    private CalculadoraProbabilidadeAdocao calculadoraProbabilidadeAdocao;

    @Mock
    private Pet pet;

    @Test
    public void testCalculaProbabilidadeAdocaoAlta() {

        pet = retunMockPet(TipoPet.CACHORRO, 4, 4.0f);

        CalculadoraProbabilidadeAdocao calculadoraProbabilidadeAdocao = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidadeAdocao = calculadoraProbabilidadeAdocao.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.ALTA, probabilidadeAdocao);

    }

    @Test
    public void testCalculaProbabilidadeAdocaoMedia() {

        pet = retunMockPet(TipoPet.CACHORRO,10, 9.0f);

        calculadoraProbabilidadeAdocao = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidadeAdocao = calculadoraProbabilidadeAdocao.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.MEDIA, probabilidadeAdocao);


    }

    @Test
    public void testCalculaProbabilidadeAdocaoBaixa() {

        pet = retunMockPet(TipoPet.GATO, 15, 16.0f);

        calculadoraProbabilidadeAdocao = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidadeAdocao = calculadoraProbabilidadeAdocao.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.BAIXA, probabilidadeAdocao);

    }

    private Pet retunMockPet(TipoPet tipoPet, Integer idade, Float peso) {

        Abrigo abrigo = new Abrigo(
                new CadastroAbrigoDto(
                        "Abrigo Modelo",
                        "19991555555",
                        "abrigo@abrigo.com.br"
                )
        );

        Pet pet = new Pet(
                new CadastroPetDto(
                        tipoPet,
                        "Cachorro Modelo",
                        "Viralata",
                        idade,
                        "Cinza",
                        peso
                ), abrigo
        );
        return pet;
    }

}
