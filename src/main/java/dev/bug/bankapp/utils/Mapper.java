package dev.bug.bankapp.utils;

import dev.bug.bankapp.dto.*;
import dev.bug.bankapp.model.Account;
import dev.bug.bankapp.model.AccountHistory;
import dev.bug.bankapp.model.Bank;
import dev.bug.bankapp.model.Client;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {

    public ClientDto clientTo(Client client) {
        return (client == null) ? null : new ClientDto()
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
        return (account == null) ? null : new AccountDto()
                .setAccountNumber(account.getAccountNumber())
                .setBalance(account.getBalance())
                .setClient(clientTo(account.getClient()));
    }

    public TransferDto transferTo(AccountDto creditAccount, AccountDto debitedAccount, double amount) {
        return new TransferDto()
                .setAmount(amount)
                .setCreditAccount(creditAccount)
                .setDebitAccount(debitedAccount);
    }

    public List<AccountHistoryDto> accountHistoriesTo(List<AccountHistory> accountHistories) {
        return accountHistories.stream()
                .map(this::accountHistoryTo)
                .collect(Collectors.toList());
    }

    private AccountHistoryDto accountHistoryTo(AccountHistory accountHistory) {
        return (accountHistory == null) ? null : new AccountHistoryDto()
                .setAccount(accountTo(accountHistory.getAccount()))
                .setBalanceBefore(accountHistory.getBalanceBefore())
                .setBalanceAfter(accountHistory.getBalanceAfter())
                .setOperation(accountHistory.getOperation())
                .setTime(accountHistory.getTime())
                .setTransferFrom(accountTo(accountHistory.getTransferFrom()));
    }
}
