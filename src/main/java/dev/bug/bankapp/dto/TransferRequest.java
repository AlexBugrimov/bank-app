package dev.bug.bankapp.dto;

import lombok.Data;

@Data
public class TransferRequest {

    private String creditAccountNumber;
    private String debitAccountNumber;
    private double amount;
}
