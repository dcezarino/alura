package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AbrigoDto;
import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.model.TipoPet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.service.AbrigoService;
import br.com.alura.adopet.api.service.PetService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AbrigoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private AbrigoService abrigoService;

    @Mock
    private AbrigoRepository abrigoRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private PetService petService;

    @Autowired
    private JacksonTester<CadastroAbrigoDto> jsonCadastroDto;

    @Test
    public void deveriaDevolverCodigo200ParaListarTodosOsAbrigosSemErros() throws Exception {

        when(abrigoService.listar()).thenReturn(mockListAbrigos());

        var response = mvc.perform(
                get("/abrigos")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());

    }

    @Test
    public void deveriaDevolverCodigo400ParaCadastrarAbrigoComErros() throws Exception {

        CadastroAbrigoDto dto = new CadastroAbrigoDto("Abrigo teste", "19991055555", "abrigo@abrigo.com.br");

        when(abrigoRepository.existsByNomeOrTelefoneOrEmail(dto.nome(), dto.telefone(), dto.email())).thenReturn(true);

        var response = mvc.perform(
                post("/abrigos")
                        .content(jsonCadastroDto.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
        assertEquals("Dados já cadastrados para outro abrigo!", response.getContentAsString());

    }

    @Test
    public void deveriaDevolverCodigo200ParaCadastrarAbrigoSemErros() throws Exception {

        CadastroAbrigoDto dto = new CadastroAbrigoDto("Abrigo teste", "19991055555", "abrigo@abrigo.com.br");

        var response = mvc.perform(
                post("/abrigos")
                        .content(jsonCadastroDto.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());

    }

    @Test
    public void deveriaDevolverCodigo200ParaListarPetsSemErros() throws Exception {

        when(abrigoService.listarPetsDoAbrigo(anyString())).thenReturn(returnMockPet());

        var response = mvc.perform(
                get("/abrigos/1/pets")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());

    }

    private List<AbrigoDto> mockListAbrigos() {

        return List.of(
                new AbrigoDto(1L, "Abrigo modelo"),
                new AbrigoDto(2L, "Abrigo modelo novo")
        );

    }

    private List<PetDto> returnMockPet() {

        return List.of(new PetDto(1l, TipoPet.CACHORRO, "Mariano", "Vira Lata", 15));

    }


}