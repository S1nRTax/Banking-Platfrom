package io.github.S1nRTax.transactions_generator.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.S1nRTax.transactions_generator.dto.GenerationResponse;
import io.github.S1nRTax.transactions_generator.model.Transaction;
import io.github.S1nRTax.transactions_generator.service.GeneratorService;
import io.github.S1nRTax.transactions_generator.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

	private final KafkaProducerService kafkaProducerService;

	@PostMapping("/generate")
	ResponseEntity<GenerationResponse> generateTransactions(@RequestParam(defaultValue = "1") int count) {

		if (count < 1 || count > 100) {
			return ResponseEntity.badRequest()
					.body(GenerationResponse.builder()
							.success(false)
							.message("Count must be between 1 and 100")
							.build());
		}

		// Generating the transactions
		List<Transaction> transactions = GeneratorService.generate(count);

		// Publishing the transactions
		kafkaProducerService.sendTransactions(transactions);

		return ResponseEntity.ok(GenerationResponse.builder()
				.success(true)
				.message(count + " transaction(s) generated successfully")
				.count(transactions.size())
				.transactions(transactions)
				.build());
	}

}
