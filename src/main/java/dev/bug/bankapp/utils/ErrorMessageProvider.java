package dev.bug.bankapp.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "error-messages")
public class ErrorMessageProvider {

    private String bankNotExists;
    private String clientExists;
    private String clientNotFound;
    private String inBankNotFoundClients;
    private String notEnoughFunds;
    private String accountNotFound;
}
