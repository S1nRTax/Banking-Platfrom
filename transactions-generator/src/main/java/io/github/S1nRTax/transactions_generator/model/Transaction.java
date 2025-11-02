package io.github.S1nRTax.transactions_generator.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Transaction {
    
    public static enum TransactionStatus { SUCCESS, FAILED, PENDING }

    private String transactionId;
    private String accountId;
    private Double amount;
    private TransactionStatus status;
    private Timestamp timestamp;
}
