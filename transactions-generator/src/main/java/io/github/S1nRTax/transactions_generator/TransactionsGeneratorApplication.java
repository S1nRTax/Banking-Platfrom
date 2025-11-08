package io.github.S1nRTax.transactions_generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TransactionsGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionsGeneratorApplication.class, args);
	}

}
