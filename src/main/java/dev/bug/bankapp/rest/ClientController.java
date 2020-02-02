package dev.bug.bankapp.rest;

import dev.bug.bankapp.dto.AccountDto;
import dev.bug.bankapp.dto.ClientDto;
import dev.bug.bankapp.exceptions.ClientNotFoundException;
import dev.bug.bankapp.model.Account;
import dev.bug.bankapp.model.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Transactional
@RestController
@RequestMapping(value = "clients", consumes = "application/json")
public class ClientController extends ApiController {

    @GetMapping("{name}")
    public ResponseEntity<ClientDto> getClientByName(@PathVariable String name) {
        if (clientRepository.existsByName(name)) {
            ClientDto clientDto = mapper.clientTo(clientRepository.findByName(name));
            return ResponseEntity.ok(clientDto);
        }
        throw new ClientNotFoundException(messageProvider, name);
    }

    @GetMapping("search")
    public ResponseEntity<List<ClientDto>> searchClientsByContainsName(@RequestParam String name) {
        List<Client> clients = clientRepository.findAllByNameContains(name);
        if (!clients.isEmpty()) {
            List<ClientDto> clientsDto = mapper.clientsTo(clients);
            return ResponseEntity.ok(clientsDto);
        }
        throw new ClientNotFoundException(messageProvider, name);
    }

    @GetMapping("{name}/accounts")
    public ResponseEntity<List<AccountDto>> getAccountsByClientName(@PathVariable String name) {
        List<Account> accounts = accountRepository.findAllByClientName(name);
        List<AccountDto> accountsDto = mapper.accountsTo(accounts);
        return ResponseEntity.ok(accountsDto);
    }

    @PostMapping("{name}/add-account")
    public ResponseEntity<AccountDto> addAccount(@PathVariable String name) {
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
    public ResponseEntity<Void> deleteClient(@PathVariable String name) {
        if (clientRepository.existsByName(name)) {
            clientRepository.deleteByName(name);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        throw new ClientNotFoundException(messageProvider, name);
    }
}
