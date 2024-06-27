package project.workouter;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import project.workouter.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Bean;

//@SpringBootApplication

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication()
@EnableAspectJAutoProxy
public class WorkouterApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkouterApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
			}
		};
	}
}
