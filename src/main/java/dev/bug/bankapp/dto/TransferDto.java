package dev.bug.bankapp.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransferDto {

    private AccountDto creditAccount;
    private AccountDto debitedAccount;
    private double amount;
    private LocalDateTime dateTime;
}
