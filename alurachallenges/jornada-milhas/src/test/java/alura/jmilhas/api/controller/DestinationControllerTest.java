package alura.jmilhas.api.controller;

import alura.jmilhas.api.domain.destination.*;
import alura.jmilhas.api.repository.DestinationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class DestinationControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DestinationData> destinationDataJson;

    @Autowired
    private JacksonTester<DestinationDetailsData> destinationDetailsDataJson;

    @Autowired
    private JacksonTester<DestinationDataByName> destinationDataByName;

    @Autowired
    private JacksonTester<DestinationUpdateData> destinationUpdateData;

    @MockBean
    private DestinationRepository destinationRepository;

    @Test
    @DisplayName("It should return HTTP code 400 when the information are invalids.")
    @WithMockUser
    void createDestination_scenario1() throws Exception {

        var response = mvc.perform(post("/destinations"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("It should return HTTP code 400 when the information are invalids.")
    @WithMockUser
    void updateDestination_scenario2() throws Exception {

        var response = mvc.perform(put("/destinations"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("It should return HTTP code 200 when the information are valids.")
    @WithMockUser
    void createDestination_scenario3() throws Exception {

        var destinationData = new DestinationData("image_url1","image_url1", "Lisboa", "Historico", "Lisboa e a capital de Portugal, situada na costa. Do imponente Castelo de Sao Jorge, a vista abrange as construcoes em tons pastel da cidade antiga, o estuario do Tejo e a Ponte 25 de Abril.", new BigDecimal(5000.00));
        var destinationDetails = new DestinationDetailsData(null, "image_url1","image_url1", "Lisboa", "Historico", "Lisboa e a capital de Portugal, situada na costa. Do imponente Castelo de Sao Jorge, a vista abrange as construcoes em tons pastel da cidade antiga, o estuario do Tejo e a Ponte 25 de Abril.", new BigDecimal(5000.00));

        when(destinationRepository.save(any())).thenReturn(new Destination(destinationData));

        var response = mvc
                .perform(
                        post("/destinations")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(destinationDataJson.write(destinationData).getJson()))
                .andReturn().getResponse();

        var jsonExpected = destinationDetailsDataJson.write(destinationDetails).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonExpected);

    }

    @Test
    @DisplayName("It should return HTTP code 200 when the information are valids.")
    @WithMockUser
    void getDestination_scenario4() throws Exception {

        var destination = new Destination(10l, "image_url1","image_url1", "Lisboa", "Historico", "Lisboa e a capital de Portugal, situada na costa. Do imponente Castelo de Sao Jorge, a vista abrange as construcoes em tons pastel da cidade antiga, o estuario do Tejo e a Ponte 25 de Abril.", new BigDecimal(5000.00), true);
        var destinationDetailsData = new DestinationDetailsData(10l, "image_url1","image_url1", "Lisboa", "Historico", "Lisboa e a capital de Portugal, situada na costa. Do imponente Castelo de Sao Jorge, a vista abrange as construcoes em tons pastel da cidade antiga, o estuario do Tejo e a Ponte 25 de Abril.", new BigDecimal(5000.00));

        when(destinationRepository.getReferenceById(10l)).thenReturn(destination);

        var response = mvc
                .perform(
                        get("/destinations/{id}", 10)).andReturn().getResponse();

        var jsonExpected = destinationDetailsDataJson.write(destinationDetailsData).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonExpected);

    }

    @Test
    @DisplayName("It should return HTTP code 200 when the information are valids.")
    @WithMockUser
    void getDestination_scenario5() throws Exception {

        var destination = new Destination(10l, "image_url1","image_url1", "Lisboa", "Historico", "Lisboa e a capital de Portugal, situada na costa. Do imponente Castelo de Sao Jorge, a vista abrange as construcoes em tons pastel da cidade antiga, o estuario do Tejo e a Ponte 25 de Abril.", new BigDecimal(5000.00), true);
        var destinationByName = new DestinationDataByName("image_url1","image_url1", "Lisboa", "Historico", "Lisboa e a capital de Portugal, situada na costa. Do imponente Castelo de Sao Jorge, a vista abrange as construcoes em tons pastel da cidade antiga, o estuario do Tejo e a Ponte 25 de Abril.", new BigDecimal(5000.00));

        when(destinationRepository.findDestinationByName("Portugal")).thenReturn(destination);

        var response = mvc
                .perform(
                        get("/destinations/findByName/{name}", "Portugal")).andReturn().getResponse();

        var jsonExpected = destinationDataByName.write(destinationByName).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonExpected);

    }

    @Test
    @DisplayName("It should return HTTP code 404 when the id is empty.")
    @WithMockUser
    void deleteDestination_scenario6() throws Exception {

        var response = mvc.perform(delete("/destinations/{id}", ""))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

    }

    @Test
    @DisplayName("It should return HTTP code 204 when the id is valid.")
    @WithMockUser
    void deleteDestination_scenario7() throws Exception {

        var destinationData = new DestinationData("image_url1","image_url1", "Lisboa", "Historico", "Lisboa e a capital de Portugal, situada na costa. Do imponente Castelo de Sao Jorge, a vista abrange as construcoes em tons pastel da cidade antiga, o estuario do Tejo e a Ponte 25 de Abril.", new BigDecimal(5000.00));

        when(destinationRepository.getReferenceById(1l)).thenReturn(new Destination(destinationData));

        var response = mvc.perform(delete("/destinations/{id}", 1))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());

    }

}