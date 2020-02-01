package dev.bug.bankapp.rest;

import dev.bug.bankapp.dto.AccountDto;
import dev.bug.bankapp.dto.TransferDto;
import dev.bug.bankapp.exceptions.AccountNotFoundException;
import dev.bug.bankapp.exceptions.NotEnoughFundsException;
import dev.bug.bankapp.model.Account;
import dev.bug.bankapp.rest.requests.MoneyTransfer;
import dev.bug.bankapp.rest.requests.MoneyWithdrawal;
import dev.bug.bankapp.utils.ErrorMessageProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.BiFunction;
import java.util.function.Function;

@Transactional
@RestController
@RequestMapping(value = "accounts", produces = "application/json", consumes = "application/json")
public class AccountController extends ApiController {

    @PostMapping("withdraw")
    public ResponseEntity<AccountDto> moneyWithdrawingFromAccount(@RequestBody MoneyWithdrawal moneyWithdrawal) {
        String accountNumber = moneyWithdrawal.getAccountNumber();
        Account account = getAnExistingAccount(accountNumber);

        double amount = moneyWithdrawal.getAmount();
        if (account.getBalance() < amount) {
            throw new NotEnoughFundsException(messageProvider, accountNumber, amount);
        }
        Account updatedAccount = accountRepository.save(account.withdraw(amount));
        AccountDto accountDto = mapper.accountTo(updatedAccount);
        return ResponseEntity.ok(accountDto);
    }

    @PostMapping("transfer")
    public ResponseEntity<TransferDto> moneyTransferBetweenAccounts(@RequestBody MoneyTransfer moneyTransfer) {
        String creditAccountNumber = moneyTransfer.getCreditAccountNumber();
        Account creditAccount = getAnExistingAccount(creditAccountNumber);
        double amount = moneyTransfer.getAmount();
        if (creditAccount.getBalance() < amount) {
            throw new NotEnoughFundsException(messageProvider, creditAccountNumber, amount);
        }

        String debitingAccountNumber = moneyTransfer.getDebitingAccountNumber();
        Account debitedAccount = getAnExistingAccount(debitingAccountNumber);

        creditAccount = accountRepository.save(creditAccount.withdraw(amount));     // Списание
        debitedAccount = accountRepository.save(debitedAccount.deposit(amount));    // Пополнение

        TransferDto transferDto = mapper.transferTo(creditAccount, debitedAccount, amount);
        return ResponseEntity.ok(transferDto);
    }

    private Account getAnExistingAccount(String accountNumber) {
        if (!accountRepository.existsByAccountNumber(accountNumber)) {
            throw new AccountNotFoundException(messageProvider, accountNumber);
        }
        return accountRepository.findByAccountNumber(accountNumber);
    }
}
