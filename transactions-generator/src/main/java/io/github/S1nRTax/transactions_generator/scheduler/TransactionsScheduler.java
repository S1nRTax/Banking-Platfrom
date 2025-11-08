package io.github.S1nRTax.transactions_generator.scheduler;

import java.util.List;
import java.util.Random;

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
	private final Random random = new Random();

	@Scheduled(fixedRate = 1000)
	public void generateAndSendTransactions() {
		try {
			int count = random.nextInt(5) + 1; // 1-5 transactions
			List<Transaction> transactions = transactionsGenerator.generate(count);

			kafkaProducerService.sendTransactions(transactions);

		} catch (Exception e) {
			log.error("Error during scheduled transactions generation: {}", e.getMessage(), e);
		}
	}
}
