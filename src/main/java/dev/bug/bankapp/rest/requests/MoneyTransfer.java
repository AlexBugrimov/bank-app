package dev.bug.bankapp.rest.requests;

import lombok.Data;

@Data
public class MoneyTransfer {

    private String creditAccountNumber;
    private String debitingAccountNumber;
    private double amount;
}
