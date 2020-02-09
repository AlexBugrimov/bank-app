package dev.bug.bankapp.web;

import dev.bug.bankapp.dto.BankDto;
import dev.bug.bankapp.model.Bank;
import dev.bug.bankapp.services.BankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("Banks")
@RestController
@RequestMapping(value = "banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping
    @ApiOperation(value = "Создать банк", response = Bank.class)
    public ResponseEntity<BankDto> createBank(
            @ApiParam(value = "Название банка", required = true, example = "Сбер Банк")
            @RequestBody Bank bank) {
        BankDto bankDto = bankService.createBank(bank);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bankDto);
    }

    @GetMapping("/{bankId}/client_names")
    @ApiOperation(value = "Получить имена клиентов банка", response = List.class)
    public ResponseEntity<List<String>> getClientNames(
            @ApiParam(value = "Id банка", required = true)
            @PathVariable long bankId) {
        List<String> clientNames = bankService.getClientNames(bankId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clientNames);
    }
}
