package dev.bug.bankapp.services;

import dev.bug.bankapp.dto.AccountHistoryDto;
import dev.bug.bankapp.dto.TransferReqDto;
import dev.bug.bankapp.exceptions.AccountNotFoundException;
import dev.bug.bankapp.model.Account;
import dev.bug.bankapp.model.AccountHistory;
import dev.bug.bankapp.model.AccountOperation;
import dev.bug.bankapp.repositories.AccountHistoryRepository;
import dev.bug.bankapp.repositories.AccountRepository;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    @SneakyThrows
    public void record(ProceedingJoinPoint joinPoint,
                         TransferReqDto transfer,
                         AccountOperation operation) {
        Account account = accountRepository.findByAccountNumber(transfer.getCreditAccountNumber())
                .orElseThrow(() -> new AccountNotFoundException(messageProvider, transfer.getCreditAccountNumber()));
        accountHistoryRepository.save(new AccountHistory()
                .setOperation(operation)
                .setAccount(account)
                .setBalanceBefore(account.getBalance())
                .doJoinPoint(joinPoint)
                .setTime(LocalDateTime.now())
                .setBalanceAfter(account.getBalance() + transfer.getAmount()));
    }
}
