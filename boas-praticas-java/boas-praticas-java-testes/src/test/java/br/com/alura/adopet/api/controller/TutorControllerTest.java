package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.AtualizacaoTutorDto;
import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroTutorDto;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.service.TutorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TutorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private TutorService tutorService;

    @MockBean
    private TutorRepository tutorRepository;

    @Autowired
    private JacksonTester<CadastroTutorDto> jsonCadastroDto;

    @Autowired
    private JacksonTester<AtualizacaoTutorDto> jsonUpdateDto;

    @Test
    public void deveriaDevolverCodigo400ParaCadastrarTutorComErros() throws Exception {

        String json = "{}";

        var response = mvc.perform(
                post("/tutores")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());

    }

    @Test
    public void deveriaDevolverCodigo200ParaCadastrarTutorSemErros() throws Exception {

        CadastroTutorDto dto = new CadastroTutorDto("Tutor Modelo", "19991055555", "tutor@tutor.com.br");

        when(tutorRepository.existsByTelefoneOrEmail(dto.telefone(), dto.email())).thenReturn(false);

        var response = mvc.perform(
                post("/tutores")
                        .content(jsonCadastroDto.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        verify(tutorRepository).save(new Tutor(dto));

    }

    @Test
    public void deveriaDevolverCodigo400ParaAtualizarTutorComErros() throws Exception {

        String json = "{}";

        var response = mvc.perform(
                put("/tutores")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());

    }

    @Test
    public void deveriaDevolverCodigo200ParaAtualizarTutorSemErros() throws Exception {

        AtualizacaoTutorDto dto = new AtualizacaoTutorDto(1l, "Tutor Alterado", "19991059955", "tutor@modelo.com.br");

        when(tutorRepository.getReferenceById(dto.id())).thenReturn(mockTutor());

        var response = mvc.perform(
                put("/tutores")
                        .content(jsonUpdateDto.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());

    }

    private Tutor mockTutor() {
        return new Tutor(new CadastroTutorDto("Tutor Modelo", "19991059955", "tutor@modelo.com.br"));
    }

}