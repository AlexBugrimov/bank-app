package dev.bug.bankapp.dto;

import dev.bug.bankapp.model.AccountOperation;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountHistoryDto {

    private AccountDto account;
    private AccountOperation operation;
    private LocalDateTime time;
    private double balanceBefore;
    private double balanceAfter;
    private AccountDto transferFrom;
}
