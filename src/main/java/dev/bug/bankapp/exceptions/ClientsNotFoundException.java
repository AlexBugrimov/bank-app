package dev.bug.bankapp.exceptions;

import dev.bug.bankapp.utils.ErrorMessageProvider;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClientsNotFoundException extends BankException {

    public ClientsNotFoundException(ErrorMessageProvider errorMessageProvider, long bankId) {
        super(errorMessageProvider.getClientsNotFound(), bankId);
    }
}
