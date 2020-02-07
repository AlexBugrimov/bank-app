package dev.bug.bankapp.model;

import dev.bug.bankapp.dto.TransferRequest;
import dev.bug.bankapp.services.ReportService;

public enum AccountOperation {

    WITHDRAW {
        @Override
        public void toDo(TransferRequest transfer, AccountHistory accountHistory ) {
            reportService.recordBefore(transfer, this);
        }
    }, DEPOSIT {
        @Override
        public void toDo(TransferRequest transfer, AccountHistory accountHistory ) {
        }
    }, TRANSFER {
        @Override
        public void toDo(TransferRequest transfer, AccountHistory accountHistory ) {
        }
    };

    public abstract void toDo(TransferRequest transfer, AccountHistory accountHistory );
}

/*

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

* */