package dev.bug.bankapp.exceptions;

import dev.bug.bankapp.utils.ErrorMessageProvider;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClientNotFoundException extends BankException {

    public ClientNotFoundException(ErrorMessageProvider errorMessageProvider, String clientName) {
        super(errorMessageProvider.getClientNotFound(), clientName);
    }
}
