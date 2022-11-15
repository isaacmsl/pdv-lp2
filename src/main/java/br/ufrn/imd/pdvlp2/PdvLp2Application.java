package br.ufrn.imd.pdvlp2;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.imd.pdvlp2.product.repository.ProductRepository;

@RestController
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableMongoRepositories
public class PdvLp2Application{

	@Autowired
	ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(PdvLp2Application.class, args);

	}

}
