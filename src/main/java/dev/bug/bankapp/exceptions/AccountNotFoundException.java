package dev.bug.bankapp.exceptions;

import dev.bug.bankapp.utils.ErrorMessageProvider;

public class AccountNotFoundException extends BankException {

    public AccountNotFoundException(ErrorMessageProvider messageProvider, String accountNumber) {
        super(messageProvider.getAccountNotFound(), accountNumber);
    }
}
