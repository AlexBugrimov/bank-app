package dev.bug.bankapp.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class NotEnoughFundsException extends BankException {

    private long id;
    private double balance;
    private double amount;

}
