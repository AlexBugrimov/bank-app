package dev.bug.bankapp.utils;

import dev.bug.bankapp.dto.AccountDto;
import dev.bug.bankapp.dto.BankDto;
import dev.bug.bankapp.dto.ClientDto;
import dev.bug.bankapp.dto.TransferDto;
import dev.bug.bankapp.model.Account;
import dev.bug.bankapp.model.Bank;
import dev.bug.bankapp.model.Client;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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

    public List<AccountDto> accountsTo(List<Account> accounts) {
        return accounts.stream()
                .map(this::accountTo)
                .collect(Collectors.toList());
    }

    public BankDto bankTo(Bank bank) {
        return new BankDto()
                .setBankId(bank.getBankId());
    }

    public AccountDto accountTo(Account account) {
        return new AccountDto()
                .setAccountNumber(account.getAccountNumber())
                .setBalance(account.getBalance())
                .setClient(clientTo(account.getClient()));
    }

    public TransferDto transferTo(Account creditAccount, Account debitedAccount, double amount) {
        return new TransferDto()
                .setAmount(amount)
                .setCreditAccount(accountTo(creditAccount))
                .setDebitAccount(accountTo(debitedAccount));
    }
}
