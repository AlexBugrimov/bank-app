package dev.bug.bankapp.dto;

import lombok.Data;

@Data
public class TransferReqDto {

    private String creditAccountNumber;
    private String debitAccountNumber;
    private double amount;
}
