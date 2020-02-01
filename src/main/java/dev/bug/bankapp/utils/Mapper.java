package dev.bug.bankapp.utils;

import dev.bug.bankapp.dto.BankDto;
import dev.bug.bankapp.dto.ClientDto;
import dev.bug.bankapp.model.Bank;
import dev.bug.bankapp.model.Client;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {

    public ClientDto clientTo(Client client) {
        return new ClientDto()
                .setName(client.getName())
                .setBank(bankTo(client.getBank()));
    }

    public List<ClientDto> clientsTo(List<Client> clients) {
        return clients.stream()
                .map(this::clientTo)
                .collect(Collectors.toList());
    }

    private BankDto bankTo(Bank bank) {
        return new BankDto()
                .setBankId(bank.getBankId());
    }
}
