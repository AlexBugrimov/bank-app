package dev.bug.bankapp.exceptions;

import dev.bug.bankapp.utils.ErrorMessageProvider;

public class ClientExistsException extends BankException {

    public ClientExistsException(ErrorMessageProvider errorMessageProvider, String clientName) {
        super(errorMessageProvider.getClientExists(), clientName);
    }
}
