package dev.bug.bankapp.rest;

import dev.bug.bankapp.dto.AccountDto;
import dev.bug.bankapp.dto.TransferDto;
import dev.bug.bankapp.exceptions.AccountNotFoundException;
import dev.bug.bankapp.exceptions.NotEnoughFundsException;
import dev.bug.bankapp.model.Account;
import dev.bug.bankapp.rest.requests.Transfer;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Transactional
@RestController
@RequestMapping(value = "accounts", produces = "application/json", consumes = "application/json")
public class AccountController extends ApiController {

    @PostMapping("withdraw")
    public ResponseEntity<AccountDto> moneyWithdrawingFromAccount(@RequestBody Transfer transfer) {
        String accountNumber = transfer.getCreditAccountNumber();
        Account account = getAnExistingAccount(accountNumber);

        double amount = transfer.getAmount();
        if (account.getBalance() < amount) {
            throw new NotEnoughFundsException(messageProvider, accountNumber, amount);
        }
        Account updatedAccount = accountRepository.save(account.withdraw(amount));
        AccountDto accountDto = mapper.accountTo(updatedAccount);
        return ResponseEntity.ok(accountDto);
    }

    @PostMapping("transfer")
    public ResponseEntity<TransferDto> moneyTransferBetweenAccounts(@RequestBody Transfer transfer) {
        String creditAccountNumber = transfer.getCreditAccountNumber();
        Account creditAccount = getAnExistingAccount(creditAccountNumber);
        double amount = transfer.getAmount();
        if (creditAccount.getBalance() < amount) {
            throw new NotEnoughFundsException(messageProvider, creditAccountNumber, amount);
        }

        String debitingAccountNumber = transfer.getDebitAccountNumber();
        Account depositAccount = getAnExistingAccount(debitingAccountNumber);

        creditAccount = accountRepository.save(creditAccount.withdraw(amount));     // Списание
        depositAccount = accountRepository.save(depositAccount.deposit(amount));    // Пополнение

        TransferDto transferDto = mapper.transferTo(creditAccount, depositAccount, amount);
        return ResponseEntity.ok(transferDto);
    }

    @PostMapping("deposit")
    public ResponseEntity<AccountDto> moneyDepositToAccount(@RequestBody Transfer transfer) {
        String accountNumber = transfer.getDebitAccountNumber();
        Account account = getAnExistingAccount(accountNumber);
        double amount = transfer.getAmount();
        Account updatedAccount = accountRepository.save(account.deposit(amount));
        AccountDto accountDto = mapper.accountTo(updatedAccount);
        return ResponseEntity.ok(accountDto);
    }

    @DeleteMapping("{accountNumber}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String accountNumber) {
        if (accountRepository.existsByAccountNumber(accountNumber)) {
            accountRepository.deleteByAccountNumber(accountNumber);
            return ResponseEntity.noContent().build();
        }
        throw new AccountNotFoundException(messageProvider, accountNumber);
    }

    private Account getAnExistingAccount(String accountNumber) {
        if (!accountRepository.existsByAccountNumber(accountNumber)) {
            throw new AccountNotFoundException(messageProvider, accountNumber);
        }
        return accountRepository.findByAccountNumber(accountNumber);
    }
}
