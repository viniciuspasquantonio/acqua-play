package br.com.pasquantonio.vinicius;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"br.com.pasquantonio.vinicius.entity"})
@EnableJpaRepositories(basePackages = {"br.com.pasquantonio.vinicius.repository"})
public class AcquaPlayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcquaPlayApplication.class, args);
	}
}
