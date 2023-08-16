package alura.jmilhas.api.controller;

import alura.jmilhas.api.domain.testimonial.Testimonial;
import alura.jmilhas.api.domain.testimonial.TestimonialDetailsData;
import alura.jmilhas.api.domain.testimonial.TestimonialRegistrationData;
import alura.jmilhas.api.repository.TestimonialRepository;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TestimonialControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<TestimonialRegistrationData> testimonialRegistrationDataJacksonJson;

    @Autowired
    private JacksonTester<TestimonialDetailsData> testimonialDetailsDataJacksonTester;

    @MockBean
    private TestimonialRepository testimonialRepository;

    @Test
    @DisplayName("It should return HTTP code 400 when the information are invalids.")
    @WithMockUser
    void createTestimonial_scenario1() throws Exception {
    	
        var response = mvc.perform(post("/testimonials"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
    
    @Test
    @DisplayName("It should return HTTP code 400 when the information are invalids.")
    @WithMockUser
    void updateTestimonial_scenario2() throws Exception {
    	
        var response = mvc.perform(put("/testimonials"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
    
    @Test
    @DisplayName("It should return HTTP code 200 when the information are valids.")
    @WithMockUser
    void createTestimonial_scenario3() throws Exception {
    	
        var testimonialData = new TestimonialRegistrationData("https://encurtador.com.br/biuvM", "Excelente site de buscas de viagens.", "Wilson da Silva");
        var testimonialDetailsData = new TestimonialDetailsData(null, "https://encurtador.com.br/biuvM", "Excelente site de buscas de viagens.", "Wilson da Silva");
        
        when(testimonialRepository.save(any())).thenReturn(new Testimonial(testimonialData));

        var response = mvc
                .perform(
                        post("/testimonials")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(testimonialRegistrationDataJacksonJson.write(testimonialData).getJson()))
                .andReturn().getResponse();

        var jsonExpected = testimonialDetailsDataJacksonTester.write(testimonialDetailsData).getJson();
        
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonExpected);
        
    }
    
    @Test
    @DisplayName("It should return HTTP code 200 when the information are valids.")
    @WithMockUser
    void getTestimonial_scenario4() throws Exception {

        var testimonialData = new TestimonialRegistrationData("https://encurtador.com.br/biuvM", "Excelente site de buscas de viagens.", "Wilson da Silva");
        var testimonialDetails = new TestimonialDetailsData(null, "https://encurtador.com.br/biuvM", "Excelente site de buscas de viagens.", "Wilson da Silva");
        
        when(testimonialRepository.getReferenceById(10l)).thenReturn(new Testimonial(testimonialData));

        var response = mvc
                .perform(
                        get("/testimonials/{id}", 10)).andReturn().getResponse();

        var jsonExpected = testimonialDetailsDataJacksonTester.write(testimonialDetails).getJson();
        
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonExpected);
        
    }
        
    @Test
    @DisplayName("It should return HTTP code 404 when the id is empty.")
    @WithMockUser
    void deleteTestimonial_scenario5() throws Exception {
    	
        var response = mvc.perform(delete("/testimonials/{id}", ""))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    
    }
    
    @Test
    @DisplayName("It should return HTTP code 204 when the id is valid.")
    @WithMockUser
    void deleteTestimonial_scenario6() throws Exception {
    	
        var testimonialData = new TestimonialRegistrationData("https://encurtador.com.br/biuvM", "Excelente site de buscas de viagens.", "Wilson da Silva");
    	
    	when(testimonialRepository.getReferenceById(1l)).thenReturn(new Testimonial(testimonialData));
    	
        var response = mvc.perform(delete("/testimonials/{id}", 1))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    	
    }
    
    @Test
    @DisplayName("It should return HTTP code 200 when the information are valids.")
    @WithMockUser
    void putTestimonial_scenario7() throws Exception {

        var testimonialData = new TestimonialRegistrationData("https://encurtador.com.br/biuvM", "Excelente site de buscas de viagens.", "Wilson da Silva");
        var testimonialDataUpdate = new TestimonialDetailsData(10l, "https://encurtador.com.br/biuvM", "Excelente site de buscas de viagens.", "Wilson da Silva");
        var testimonialUpdatedData = new TestimonialDetailsData(null, "https://encurtador.com.br/biuvM", "Excelente site de buscas de viagens.", "Wilson da Silva");

        when(testimonialRepository.getReferenceById(10l)).thenReturn(new Testimonial(testimonialData));

        var response = mvc
                .perform(
                        put("/testimonials")
                .contentType(MediaType.APPLICATION_JSON)
                .content(testimonialDetailsDataJacksonTester.write(testimonialDataUpdate).getJson()))
                .andReturn().getResponse();

        var jsonExpected = testimonialDetailsDataJacksonTester.write(testimonialUpdatedData).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonExpected);


    }
}