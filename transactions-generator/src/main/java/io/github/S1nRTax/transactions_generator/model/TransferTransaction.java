package io.github.S1nRTax.transactions_generator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class TransferTransaction  extends Transaction {

    private String receiverAccountId;
    private boolean isFirstTime;
    
}
