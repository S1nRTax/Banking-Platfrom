package io.github.S1nRTax.transactions_generator.dto;

import java.util.List;

import io.github.S1nRTax.transactions_generator.model.Transaction;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenerationResponse {
    private boolean success;
    private String message;
    private int count;
    private List<Transaction> transactions;
}
