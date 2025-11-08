package io.github.S1nRTax.transactions_generator.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.github.S1nRTax.transactions_generator.model.PaymentTransaction;
import io.github.S1nRTax.transactions_generator.model.ServiceTransaction;
import io.github.S1nRTax.transactions_generator.model.ServiceTransaction.ServiceType;
import io.github.S1nRTax.transactions_generator.model.Transaction;
import io.github.S1nRTax.transactions_generator.model.Transaction.TransactionStatus;
import io.github.S1nRTax.transactions_generator.model.TransferTransaction;

@Service
public class GeneratorService {
   
   private static final Random RANDOM = new Random();

   public List<Transaction> generate(int count){
	List<Transaction> transactions = new ArrayList<>();
	for(int i=0; i<count; i++){
	    transactions.add(generateSingleTransaction());
	}

	return transactions;
    }
    
    private static Transaction generateSingleTransaction(){
	int index = RANDOM.nextInt(2);
	if(index == 0 ) return PaymentTransaction.builder()
		.transactionId(randomTransactionId())
		.accountId(randomAccountId())
		.amount(randomAmount())
		.status(randomStatus())
		.timestamp(new Timestamp(System.currentTimeMillis()))
		.serviceName(randomServiceName())
		.serviceCategory(randomServiceCategory())
		.build();
	else if(index == 1 ) return  ServiceTransaction.builder()
		.transactionId(randomTransactionId())
		.accountId(randomAccountId())
		.amount(randomAmount())
		.status(randomStatus())
		.timestamp(new Timestamp(System.currentTimeMillis()))
		.type(randomServiceType())
		.location(randomLocation())
		.build();
	else return TransferTransaction.builder()
		.transactionId(randomTransactionId())
		.accountId(randomAccountId())
		.amount(randomAmount())
		.status(randomStatus())
		.timestamp(new Timestamp(System.currentTimeMillis()))
		.receiverAccountId(randomAccountId())
		.isFirstTime(RANDOM.nextBoolean())
		.build();
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
	String part2 = uuid.substring(4, 8);
	String part3 = uuid.substring(8, 12);
	
	return String.format("ACC-%s-%s-%s",part1, part2, part3);
    }
    
    private static Double randomAmount(){
	double mean = 4.5;
	double stdDev = 1.5;

	double amount = Math.exp(mean + stdDev * RANDOM.nextGaussian());
	amount = Math.max(0.99, Math.min(5000, amount));

	return Math.round(amount * 100.0) / 100.0;
    }
    
    private static TransactionStatus randomStatus(){
	TransactionStatus[] allStatus = TransactionStatus.values();	
	return allStatus[RANDOM.nextInt(allStatus.length)];

    }

    private static String randomServiceName(){
	 String[] listOfServices = {
        // Telecom
        "Maroc Telecom", "Orange Morocco", "Inwi",
        
        // Retail & Supermarkets
        "Marjane", "Acima", "Carrefour Market", "BIM", "Atacadao",
        
        // E-commerce
        "Jumia", "Avito", "Glovo", "Wabi",
        
        // Restaurants & Food Delivery
        "McDonald's", "KFC", "Pizza Hut", "Burger King", 
        "Tacos de Lyon", "Paul", "Amal Food",
        
        // Entertainment & Streaming
        "Netflix", "Spotify", "Shahid", "OSN", "YouTube Premium",
        "Anghami", "StarzPlay",
        
        // Transport
        "Tram", "Careem", "InDrive", "ONCF", "CTM", "Taxi",
        
        // Utilities
        "Redal", "Lydec", "Amendis", "RADEEMA",
        
        // Banks & Financial
        "Attijariwafa Bank", "BMCE", "Banque Populaire", 
        "CIH Bank", "Crédit du Maroc",
        
        // Health & Pharmacy
        "Pharmacie Principale", "Doctorlib", "Multilab",
        
        // Education
        "Edunet", "AlMadrassa", "9rayti",
        
        // Gas Stations
        "Afriquia", "Total Morocco", "Shell Morocco", "Winxo" };
	return listOfServices[RANDOM.nextInt(listOfServices.length)];
    }
    
    private static String randomServiceCategory(){
	String[] categories = {
        "Telecommunications",
        "Retail & Shopping",
        "E-commerce",
        "Food & Restaurants",
        "Entertainment & Media",
        "Transportation",
        "Utilities & Bills",
        "Banking & Finance",
        "Healthcare & Pharmacy",
        "Education",
        "Fuel & Gas",
        "Groceries",
        "Fashion & Clothing",
        "Electronics",
        "Home & Garden",
        "Insurance",
        "Government Services",
        "Subscription Services"
	};
	return categories[RANDOM.nextInt(categories.length)];
    }

    private static ServiceType randomServiceType(){
	ServiceType[] types = ServiceType.values();
	return types[RANDOM.nextInt(types.length)];
    }


    private static String randomLocation(){
	String[] locations = {
        // Major Cities
        "Casablanca", "Rabat", "Marrakech", "Fes", "Tangier",
        "Agadir", "Meknes", "Oujda", "Kenitra", "Tetouan",
        
        // Other Cities
        "Safi", "El Jadida", "Nador", "Mohammedia", "Khouribga",
        "Beni Mellal", "Taza", "Settat", "Larache", "Ksar El Kebir",
        
        // Regions 
        "Casablanca-Settat", "Rabat-Sale-Kenitra", 
        "Marrakech-Safi", "Fes-Meknes", "Tangier-Tetouan-Al Hoceima",
        "Souss-Massa", "Oriental"
	};
	return locations[RANDOM.nextInt(locations.length)];
    }




}
