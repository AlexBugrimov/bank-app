package dev.bug.bankapp.exceptions;

public class ClientExistsException extends BankException {

    public ClientExistsException(String message) {
        super(message);
    }
}
