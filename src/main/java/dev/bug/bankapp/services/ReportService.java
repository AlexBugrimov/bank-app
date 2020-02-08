package dev.bug.bankapp.services;

import dev.bug.bankapp.dto.AccountHistoryDto;
import dev.bug.bankapp.dto.TransferRequest;
import dev.bug.bankapp.model.Account;
import dev.bug.bankapp.model.AccountHistory;
import dev.bug.bankapp.repositories.AccountHistoryRepository;
import dev.bug.bankapp.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService extends ApiService {

    @Autowired
    private AccountHistoryRepository accountHistoryRepository;

    @Autowired
    private AccountRepository accountRepository;

    public List<AccountHistoryDto> getAccountHistory(LocalDateTime from, LocalDateTime to) {
        return mapper.accountHistoriesTo(accountHistoryRepository
                .findAllByTimeBetween(from, to));
    }

    public void executeBefore(TransferRequest transfer, AccountHistory accountHistory) {
        Optional<Account> debitAccount = accountRepository.findByAccountNumber(transfer.getDebitAccountNumber());
        debitAccount.ifPresent(account -> accountHistory
                .setAccount(account)
                .setBalanceBefore(account.getBalance()));
        Optional<Account> creditAccount = accountRepository.findByAccountNumber(transfer.getCreditAccountNumber());
        creditAccount.ifPresent(account -> accountHistory
                .setAccount(account)
                .setBalanceBefore(account.getBalance()));
    }

    public void executeAfter(TransferRequest transfer, AccountHistory accountHistory) {
        Optional<Account> debitAccount = accountRepository.findByAccountNumber(transfer.getDebitAccountNumber());
        debitAccount.ifPresent(account -> {
            accountHistory.setTransferFrom(account).setBalanceAfter(account.getBalance());
            accountHistoryRepository.save(accountHistory.setBalanceAfter(account.getBalance()).setTime(LocalDateTime.now()));
        });
        Optional<Account> creditAccount = accountRepository.findByAccountNumber(transfer.getCreditAccountNumber());
        creditAccount.ifPresent(account -> {
            accountHistory.setTransferFrom(account).setBalanceAfter(account.getBalance());
            accountHistoryRepository.save(accountHistory.setBalanceAfter(account.getBalance()).setTime(LocalDateTime.now()));
        });
    }
}
