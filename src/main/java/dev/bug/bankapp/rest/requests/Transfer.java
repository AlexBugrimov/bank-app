package dev.bug.bankapp.rest.requests;

import lombok.Data;

@Data
public class Transfer {

    private String creditAccountNumber;
    private String debitAccountNumber;
    private double amount;
}
