package dev.bug.bankapp.exceptions;

import dev.bug.bankapp.utils.ErrorMessageProvider;

public class BankNotExistsException extends BankException {

    public BankNotExistsException(ErrorMessageProvider errorMessageProvider, long bankId) {
        super(errorMessageProvider.getBankNotExists(), bankId);
    }
}
