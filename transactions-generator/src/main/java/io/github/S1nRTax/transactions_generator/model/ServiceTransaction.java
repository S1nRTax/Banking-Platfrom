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

   private static enum ServiceType { DEPOSIT, WITHDRAWAL }
    
    private ServiceType type;
    private String location;
}

