package dev.bug.bankapp.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AccountDto {

    private ClientDto client;
    private String accountNumber;
    private double balance;
}
