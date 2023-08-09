package alura.jmilhas.api.infra.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfiguration implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry corsRegistry) {
		corsRegistry.addMapping("/**")
				    .allowedOrigins("*")		
				    .allowedMethods("GET, POST, PUT, DELETE")				    
	                .allowedHeaders("Authorization","Origin")
	                .exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials")
	                .allowCredentials(false)
	                .maxAge(3600);
	}

}