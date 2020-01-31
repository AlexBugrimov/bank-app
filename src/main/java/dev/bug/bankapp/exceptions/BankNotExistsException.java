package dev.bug.bankapp.exceptions;

import static dev.bug.bankapp.utils.FormattedText.text;

public class BankNotExistsException extends BankException {

    public BankNotExistsException(ErrorMessageProvider errorMessageProvider, long bankId) {
        super(text(errorMessageProvider.getBankNotExists(), bankId));
    }
}
