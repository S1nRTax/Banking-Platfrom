package io.github.S1nRTax.transactions_generator.scheduler;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.github.S1nRTax.transactions_generator.model.Transaction;
import io.github.S1nRTax.transactions_generator.service.GeneratorService;
import io.github.S1nRTax.transactions_generator.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionsScheduler {

	private final GeneratorService transactionsGenerator;

	private final KafkaProducerService kafkaProducerService;

	@Scheduled(fixedRate = 1000)
	public void generateAndSendTransactions() {
		try {
			List<Transaction> transactions = transactionsGenerator.generate(1);

			kafkaProducerService.sendTransactions(transactions);

		} catch (Exception e) {
			log.error("Error during scheduled transactions generation: {}", e.getMessage(), e);
		}
	}
}
