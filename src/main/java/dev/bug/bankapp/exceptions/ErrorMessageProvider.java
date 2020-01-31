package dev.bug.bankapp.exceptions;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "error-messages")
public class ErrorMessageProvider {

    private String bankNotExists;
    private String clientExists;
}
