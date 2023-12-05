package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.ReprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.service.AdocaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AdocaoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AdocaoService service;

    @Autowired
    private JacksonTester<SolicitacaoAdocaoDto> jsonSolicitacaoDto;

    @Autowired
    private JacksonTester<AprovacaoAdocaoDto> jsonAprovacaoDto;

    @Autowired
    private JacksonTester<ReprovacaoAdocaoDto> jsonReprovacaoDto;

    @Test
    public void deveriaDevolverCodigo400ParaSolicitacaoDeAdocaoComErros() throws Exception {

        String json = "{}";

        var response = mvc.perform(
                post("/adocoes")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());


    }

    @Test
    public void deveriaDevolverCodigo200ParaSolicitacaoDeAdocaoSemErros() throws Exception {

        SolicitacaoAdocaoDto dto = new SolicitacaoAdocaoDto(1l, 1l, "Motivo teste");

        var response = mvc.perform(
                post("/adocoes")
                        .content(jsonSolicitacaoDto.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("Adoção solicitada com sucesso!", response.getContentAsString());



    }

    @Test
    public void deveriaDevolverCodigo400ParaAprovacaoDeAdocaoComErros() throws Exception {

       String json = "{}";

        var response = mvc.perform(
                put("/adocoes/aprovar")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());

    }

   @Test
    public void deveriaDevolverCodigo200ParaAprovacaoDeAdocaoSemErros() throws Exception {

        AprovacaoAdocaoDto dto = new AprovacaoAdocaoDto(1l);

        var response = mvc.perform(
                put("/adocoes/aprovar")
                        .content(jsonAprovacaoDto.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("Adoção aprovada com sucesso!", response.getContentAsString());

    }

    @Test
    public void deveriaDevolverCodigo400ParaReprovacaoDeAdocaoComErros() throws Exception {

        String json = "{}";

        var response = mvc.perform(
                put("/adocoes/reprovar")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());

    }

    @Test
    public void deveriaDevolverCodigo200ParaReprovacaoDeAdocaoSemErros() throws Exception {

        ReprovacaoAdocaoDto dto = new ReprovacaoAdocaoDto(1l, "Motivo reprovação.");

        var response = mvc.perform(
                put("/adocoes/reprovar")
                        .content(jsonReprovacaoDto.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("Adoção reprovada com sucesso!", response.getContentAsString());

    }


}