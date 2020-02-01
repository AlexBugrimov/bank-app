package dev.bug.bankapp.rest;

import dev.bug.bankapp.dto.AccountDto;
import dev.bug.bankapp.exceptions.AccountNotFoundException;
import dev.bug.bankapp.exceptions.NotEnoughFundsException;
import dev.bug.bankapp.model.Account;
import dev.bug.bankapp.rest.requests.MoneyWithdrawal;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@RestController
@RequestMapping(value = "accounts", produces = "application/json", consumes = "application/json")
public class AccountController extends ApiController {

    @PostMapping
    public ResponseEntity<AccountDto> withdrawingMoneyFromAccount(@RequestBody MoneyWithdrawal moneyWithdrawal) {
        final String accountNumber = moneyWithdrawal.getAccountNumber();
        final double amount = moneyWithdrawal.getAmount();
        if (accountRepository.existsByAccountNumber(accountNumber)) {
            Account account = accountRepository.findByAccountNumber(accountNumber);
            if (account.getBalance() < amount) {
                throw new NotEnoughFundsException(messageProvider, accountNumber, amount);
            }
            account.withdraw(amount);
            Account updatedAccount = accountRepository.save(account);
            AccountDto accountDto = mapper.accountTo(updatedAccount);
            return ResponseEntity.ok(accountDto);
        }
        throw new AccountNotFoundException(messageProvider, accountNumber);
    }

}
