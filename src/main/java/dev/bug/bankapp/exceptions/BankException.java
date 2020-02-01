package dev.bug.bankapp.exceptions;

import static dev.bug.bankapp.utils.FormattedText.text;

public abstract class BankException extends RuntimeException {

    public BankException(String message, Object... values) {
        super(text(message, values));
    }
}
