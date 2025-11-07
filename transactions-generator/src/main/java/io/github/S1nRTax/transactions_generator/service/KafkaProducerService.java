package io.github.S1nRTax.transactions_generator.service;

import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import io.github.S1nRTax.transactions_generator.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {

	private static final String TOPIC = "transactions";

	private final KafkaTemplate<String, Transaction> kafkaTemplate;

	public void sendTransaction(Transaction transaction) {
		kafkaTemplate.send(TOPIC, transaction.getTransactionId(), transaction)
				.whenComplete((result, ex) -> {
					if (ex == null) {
						log.info("Sent transaction {} to topic {}", transaction.getTransactionId(), TOPIC);
					} else {
						log.error("Failed to sent transaction {}: {}", transaction.getTransactionId(), ex.getMessage());
					}
				});
	}

	public void sendTransactions(List<Transaction> transactions) {
		transactions.forEach(this::sendTransaction);
	}
}
