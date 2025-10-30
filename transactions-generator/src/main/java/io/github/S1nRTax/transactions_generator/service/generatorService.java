package io.github.S1nRTax.transactions_generator.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import io.github.S1nRTax.transactions_generator.model.Transaction;
import io.github.S1nRTax.transactions_generator.model.TransferTransaction;
import io.github.S1nRTax.transactions_generator.model.ServiceTransaction;
import io.github.S1nRTax.transactions_generator.model.PaymentTransaction;

@Service
public class generatorService {
   
   private static int distribution = 0;

   public static List<Transaction> generate(int count){
	List<Transaction> transactions = new ArrayList<>();

	for(int i=0; i < count; i++){

	PaymentTransaction payment = PaymentTransaction.builder()
		.transactionId(randomTransactionId())
		.accountId(randomAccountId())
		.amount(randomAmount())
		.status(Transaction.TransactionStatus.SUCCESS)
		.timestamp(new Timestamp(System.currentTimeMillis()))
		.serviceName("Netflix")
		.serviceCategory("Entertainment")
		.productType("Subscription")
		.build();

	transactions.add(payment);

	}

	return transactions;
    }


    private static String randomTransactionId(){
	String uuid = UUID.randomUUID().toString().replace("-", "");
	String part1 = uuid.substring(0, 4);
	String part2 = uuid.substring(1, 8);
	String part3 = uuid.substring(8, 12);

	return String.format("TXN-%s-%s-%s",part1, part2, part3);
    }
    
    private static String randomAccountId(){
	String uuid = UUID.randomUUID().toString().replace("-", "");
	String part1 = uuid.substring(0, 4);
	String part2 = uuid.substring(1, 8);
	String part3 = uuid.substring(8, 12);
	
	return String.format("ACC-%s-%s-%s",part1, part2, part3);
    }
    
    private static Double randomAmount(){
	Random rand = ThreadLocalRandom.current();
	double mean = 4.5;
	double stdDev = 1.5;

	double amount = Math.exp(mean + stdDev * rand.nextGaussian());
	amount = Math.max(0.99, Math.min(5000, amount));

	return Math.round(amount * 100.0) / 100.0;
    }


}
