package dev.bug.bankapp.exceptions;

public abstract class BankException extends RuntimeException {

    public BankException(String message) {
        super(message);
    }
}
