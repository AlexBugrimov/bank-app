package dev.bug.bankapp.rest;

import dev.bug.bankapp.dto.AccountDto;
import dev.bug.bankapp.dto.ClientDto;
import dev.bug.bankapp.exceptions.ClientNotFoundException;
import dev.bug.bankapp.model.Account;
import dev.bug.bankapp.model.Bank;
import dev.bug.bankapp.model.Client;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Api("Клиент банка")
@Transactional
@RestController
@RequestMapping(value = "clients", consumes = "application/json")
public class ClientController extends ApiController {

    @GetMapping("{name}")
    @ApiOperation(value = "Получить клиента по имени", response = ClientDto.class)
    public ResponseEntity<ClientDto> getClientByName(
            @ApiParam(value = "Имя клиента", required = true)
            @PathVariable String name) {
        if (clientRepository.existsByName(name)) {
            ClientDto clientDto = mapper.clientTo(clientRepository.findByName(name));
            return ResponseEntity.ok(clientDto);
        }
        throw new ClientNotFoundException(messageProvider, name);
    }

    @GetMapping
    @ApiOperation(value = "Получить клиентов если они содержат часть имени", response = List.class)
    public ResponseEntity<List<ClientDto>> getClientsByContainsName(
            @ApiParam(value = "Часть имени клиента", required = true)
            @RequestParam String name) {
        List<Client> clients = clientRepository.findAllByNameContains(name);
        if (!clients.isEmpty()) {
            List<ClientDto> clientsDto = mapper.clientsTo(clients);
            return ResponseEntity.ok(clientsDto);
        }
        throw new ClientNotFoundException(messageProvider, name);
    }

    @GetMapping("{name}/accounts")
    @ApiOperation(value = "Получить список счетов клиента по его имени", response = List.class)
    public ResponseEntity<List<AccountDto>> getAccountsByClientName(
            @ApiParam(value = "Имя клиента", required = true)
            @PathVariable String name) {
        List<Account> accounts = accountRepository.findAllByClientName(name);
        List<AccountDto> accountsDto = mapper.accountsTo(accounts);
        return ResponseEntity.ok(accountsDto);
    }

    @PostMapping("{name}/add-account")
    @ApiOperation(value = "Добавить счет клиенту", response = AccountDto.class)
    public ResponseEntity<AccountDto> addAccount(
            @ApiParam(value = "Имя клиента", required = true)
            @PathVariable String name) {
        if (clientRepository.existsByName(name)) {
            Client client = clientRepository.findByName(name);
            Account account = new Account()
                    .setAccountNumber(UUID.randomUUID().toString())
                    .setClient(client)
                    .setBalance(0.0);
            Account newAccount = accountRepository.save(account);
            client.addAccount(newAccount);
            clientRepository.save(client);
            AccountDto accountDto = mapper.accountTo(newAccount);
            return ResponseEntity.status(HttpStatus.CREATED).body(accountDto);
        }
        throw new ClientNotFoundException(messageProvider, name);
    }

    @DeleteMapping("{name}")
    @ApiOperation(value = "Удаление клиента")
    public ResponseEntity<Void> deleteClient(
            @ApiParam(value = "Имя клиента", required = true)
            @PathVariable String name) {
        if (clientRepository.existsByName(name)) {
            clientRepository.deleteByName(name);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        throw new ClientNotFoundException(messageProvider, name);
    }
}
