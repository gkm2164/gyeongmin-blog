package kr.pe.imarch;

import kr.pe.imarch.security.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(WebSecurityConfig.class)
public class GyeongminBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(GyeongminBlogApplication.class, args);
	}
}
