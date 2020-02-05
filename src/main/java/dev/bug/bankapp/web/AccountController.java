package dev.bug.bankapp.web;

import dev.bug.bankapp.dto.AccountDto;
import dev.bug.bankapp.dto.TransferDto;
import dev.bug.bankapp.dto.TransferReqDto;
import dev.bug.bankapp.services.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api("Account")
@RestController
@RequestMapping(value = "accounts",
        produces = "application/json",
        consumes = "application/json")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("withdraw")
    @ApiOperation(value = "Списать со счета", response = AccountDto.class)
    public ResponseEntity<AccountDto> withdraw(@RequestBody TransferReqDto transfer) {
        AccountDto accountDto = accountService.withdraw(transfer);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountDto);
    }

    @PostMapping("deposit")
    @ApiOperation(value = "Пополнить счет", response = AccountDto.class)
    public ResponseEntity<AccountDto> deposit(@RequestBody TransferReqDto transfer) {
        AccountDto accountDto = accountService.deposit(transfer);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountDto);
    }

    @PostMapping("transfer")
    @ApiOperation(value = "Перевести со счета на счет", response = TransferDto.class)
    public ResponseEntity<TransferDto> transfer(@RequestBody TransferReqDto transfer) {
        TransferDto transferDto = accountService.transfer(transfer);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(transferDto);
    }

    @DeleteMapping("{accountNumber}")
    @ApiOperation(value = "Удалить счет")
    public ResponseEntity<Void> deleteAccount(
            @ApiParam(value = "Номер счета", required = true, example = "3ab76c49-c252-45d9-a0e3-ff0ee3a2221e")
            @PathVariable String accountNumber) {
        accountService.deleteAccountByNumber(accountNumber);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PatchMapping("add")
    @ApiOperation(value = "Добавить счет клиенту", response = AccountDto.class)
    public ResponseEntity<AccountDto> addAccount(
            @ApiParam(value = "Имя клиента", required = true)
            @PathVariable String clientName) {
        AccountDto accountDto = accountService.addAccount(clientName);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(accountDto);
    }
}
