package dev.bug.bankapp.rest.requests;

import lombok.Data;

@Data
public class MoneyWithdrawal {

    private String accountNumber;
    private double amount;
}
