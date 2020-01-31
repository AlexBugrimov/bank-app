package dev.bug.bankapp.exceptions;

import static dev.bug.bankapp.utils.FormattedText.text;

public class ClientExistsException extends BankException {

    public ClientExistsException(ErrorMessageProvider errorMessageProvider, String clientName) {
        super(text(errorMessageProvider.getClientExists(), clientName));
    }
}
