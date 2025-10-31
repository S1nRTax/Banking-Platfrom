package io.github.S1nRTax.transactions_generator.model;

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
public class ServiceTransaction extends Transaction {

    public static enum ServiceType {
    DEPOSIT,           // Money coming in
    WITHDRAWAL,        // Money going out
    BILL_PAYMENT,      // Paying utilities, phone bills
    RECHARGE,          // Mobile credit top-up
    TRANSFER,          // Money transfer
    PURCHASE,          // Buying goods/services
    REFUND,            // Money returned
    SUBSCRIPTION,      // Recurring payment
    CASH_OUT,          // ATM withdrawal
    CASH_IN            // ATM deposit
    }

    private ServiceType type;
    private String location;
}

