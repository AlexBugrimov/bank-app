package dev.bug.bankapp.exceptions;

import dev.bug.bankapp.utils.ErrorMessageProvider;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends BankException {

    public AccountNotFoundException(ErrorMessageProvider messageProvider, String accountNumber) {
        super(messageProvider.getAccountNotFound(), accountNumber);
    }
}
