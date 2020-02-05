package dev.bug.bankapp.services;

import dev.bug.bankapp.dto.AccountDto;
import dev.bug.bankapp.dto.TransferDto;
import dev.bug.bankapp.dto.TransferReqDto;
import dev.bug.bankapp.exceptions.AccountNotFoundException;
import dev.bug.bankapp.exceptions.ClientNotFoundException;
import dev.bug.bankapp.exceptions.NotEnoughFundsException;
import dev.bug.bankapp.model.Account;
import dev.bug.bankapp.model.Client;
import dev.bug.bankapp.repositories.AccountRepository;
import dev.bug.bankapp.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AccountService extends ApiService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    public List<AccountDto> getAccountsByClientName(String name) {
        List<Account> accounts = accountRepository.findAllByClientName(name);
        return mapper.accountsTo(accounts);
    }

    public AccountDto withdraw(TransferReqDto transfer) {
        String accountNumber = transfer.getCreditAccountNumber();
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(messageProvider, accountNumber));
        double amount = transfer.getAmount();
        if (account.getBalance() < amount) {
            throw new NotEnoughFundsException(messageProvider, accountNumber, amount);
        }
        return mapper.accountTo(accountRepository.save(account.withdraw(amount)));
    }

    public AccountDto deposit(TransferReqDto transfer) {
        String accountNumber = transfer.getDebitAccountNumber();
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(messageProvider, accountNumber));
        double amount = transfer.getAmount();
        return mapper.accountTo(accountRepository.save(account.deposit(amount)));
    }

    public TransferDto transfer(TransferReqDto transfer) {
        return mapper.transferTo(withdraw(transfer), deposit(transfer), transfer.getAmount());
    }

    public void deleteAccountByNumber(String accountNumber) {
        accountRepository.findByAccountNumber(accountNumber)
                .ifPresent(account -> accountRepository.deleteByAccountNumber(account.getAccountNumber()));
    }

    public AccountDto addAccount(String clientName) {
        Client client = clientRepository.findClientByName(clientName)
                .orElseThrow(() -> new ClientNotFoundException(messageProvider, clientName));
        Account account = accountRepository.save(
                new Account()
                        .setAccountNumber(UUID.randomUUID().toString())
                        .setClient(client)
                        .setBalance(0.0));
        return mapper.accountTo(account);
    }
}
