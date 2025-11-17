package fiap.com.br.lifepath;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LifepathApplication {

	public static void main(String[] args) {
		SpringApplication.run(LifepathApplication.class, args);
	}

}
