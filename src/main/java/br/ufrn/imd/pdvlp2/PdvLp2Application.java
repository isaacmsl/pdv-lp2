package br.ufrn.imd.pdvlp2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PdvLp2Application {

	public static void main(String[] args) {
		SpringApplication.run(PdvLp2Application.class, args);
	}

}
