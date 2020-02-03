package dev.bug.bankapp.dto;

import lombok.Data;

@Data
public class TransferDto {

    private AccountDto creditAccount;
    private AccountDto debitAccount;
    private double amount;
}
