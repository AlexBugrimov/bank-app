package dev.bug.bankapp.dto;

import lombok.Data;

@Data
public class AccountDto {

    private ClientDto client;
    private String accountNumber;
    private double balance;
}
