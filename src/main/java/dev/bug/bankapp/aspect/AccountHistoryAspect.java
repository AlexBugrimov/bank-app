package dev.bug.bankapp.aspect;

import dev.bug.bankapp.model.Account;
import dev.bug.bankapp.model.AccountHistory;
import dev.bug.bankapp.model.AccountHistory.AccountOperation;
import dev.bug.bankapp.repositories.AccountHistoryRepository;
import dev.bug.bankapp.repositories.AccountRepository;
import dev.bug.bankapp.rest.requests.Transfer;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Aspect
@Component
@Transactional
public class AccountHistoryAspect {

    @Autowired
    private AccountHistoryRepository accountHistoryRepository;

    @Autowired
    private AccountRepository accountRepository;

    @SneakyThrows
    private void beforeHistory(AccountHistory accountHistory, String accountNumber) {
        if (accountRepository.existsByAccountNumber(accountNumber)) {
            Account account = accountRepository.findByAccountNumber(accountNumber);
            accountHistory.setBalanceBefore(account.getBalance()).setAccount(account);
        }
    }

    @SneakyThrows
    private void afterHistory(AccountHistory accountHistory, String accountNumber, String transferAccountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        accountHistory.setTime(LocalDateTime.now())
                .setBalanceAfter(account.getBalance())
                .setTransferFrom(accountRepository.findByAccountNumber(transferAccountNumber));
    }

    @SneakyThrows
    @Around("execution(* dev.bug.bankapp.rest.AccountController.*(dev.bug.bankapp.rest.requests.Transfer)) && args(data)")
    public Object logAfterReturning(ProceedingJoinPoint joinPoint, Object data) {
        AccountOperation operation = AccountOperation.valueOf(joinPoint.getSignature().getName().toUpperCase());
        Transfer transfer = (Transfer) data;
        Object proceed = null;

        switch (operation) {
            case DEPOSIT: {
                AccountHistory accountHistory = new AccountHistory().setOperation(operation);
                String accountNumber = transfer.getDebitAccountNumber();
                beforeHistory(accountHistory, accountNumber);

                proceed = joinPoint.proceed();

                afterHistory(accountHistory, accountNumber, null);
                accountHistoryRepository.save(accountHistory);
                break;
            }
            case WITHDRAW: {
                AccountHistory accountHistory = new AccountHistory().setOperation(operation);
                String accountNumber = transfer.getCreditAccountNumber();
                beforeHistory(accountHistory, accountNumber);

                proceed = joinPoint.proceed();

                afterHistory(accountHistory, accountNumber, null);
                accountHistoryRepository.save(accountHistory);
                break;
            }
            case TRANSFER: {
                AccountHistory creditAccountHistory = new AccountHistory().setOperation(operation);
                AccountHistory debitAccountHistory = new AccountHistory().setOperation(operation);

                String creditAccountNumber = transfer.getCreditAccountNumber();
                String debitAccountNumber = transfer.getDebitAccountNumber();

                beforeHistory(creditAccountHistory, creditAccountNumber);
                beforeHistory(debitAccountHistory, debitAccountNumber);

                proceed = joinPoint.proceed();

                afterHistory(creditAccountHistory, creditAccountNumber, debitAccountNumber);
                afterHistory(debitAccountHistory, debitAccountNumber, creditAccountNumber);

                accountHistoryRepository.save(creditAccountHistory);
                accountHistoryRepository.save(debitAccountHistory);
                break;
            }
        }
        return proceed;
    }
}
