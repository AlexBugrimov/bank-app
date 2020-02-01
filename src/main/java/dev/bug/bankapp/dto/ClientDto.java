package dev.bug.bankapp.dto;

import lombok.Data;

@Data
public class ClientDto {

    private String name;
    private BankDto bank;
}
