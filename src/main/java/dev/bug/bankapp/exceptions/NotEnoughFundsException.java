package dev.bug.bankapp.exceptions;

import dev.bug.bankapp.utils.ErrorMessageProvider;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotEnoughFundsException extends BankException {

    public NotEnoughFundsException(ErrorMessageProvider messageProvider, String accountNumber, double amount) {
        super(messageProvider.getNotEnoughFunds(), accountNumber, amount);
    }
}
