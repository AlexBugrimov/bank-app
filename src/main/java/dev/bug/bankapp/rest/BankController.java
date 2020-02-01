package dev.bug.bankapp.rest;

import dev.bug.bankapp.dto.ClientDto;
import dev.bug.bankapp.exceptions.BankNotExistsException;
import dev.bug.bankapp.exceptions.ClientExistsException;
import dev.bug.bankapp.exceptions.InBankNotFoundClientsException;
import dev.bug.bankapp.model.Bank;
import dev.bug.bankapp.model.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RestController
@RequestMapping(value = "banks", consumes = "application/json")
public class BankController extends ApiController {

    @PostMapping
    public ResponseEntity<Bank> createBank() {
        Bank bank = bankRepository.save(new Bank());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bank);
    }

    @PostMapping("{bankId}")
    public ResponseEntity<ClientDto> addClient(@PathVariable long bankId,
                                               @RequestBody Client client) {
        if (!bankRepository.existsById(bankId)) {
            throw new BankNotExistsException(messageProvider, bankId);
        }
        if (clientRepository.existsByName(client.getName())) {
            throw new ClientExistsException(messageProvider, client.getName());
        }
        Bank bank = bankRepository.getOne(bankId);
        Client updatedClient = client.setBank(bank);
        ClientDto addedClient = mapper.clientTo(clientRepository.save(updatedClient));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(addedClient);
    }

    @GetMapping("/{bankId}/clients-names")
    public ResponseEntity<List<String>> getClientsNamesByBankId(@PathVariable long bankId) {
        if (!bankRepository.existsById(bankId)) {
            throw new BankNotExistsException(messageProvider, bankId);
        }
        List<Client> clients = clientRepository.findClientsByBankBankId(bankId);
        if (clients.isEmpty()) {
            throw new InBankNotFoundClientsException(messageProvider, bankId);
        }
        List<String> clientsNames = clients.stream()
                .map(Client::getName)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientsNames);
    }
}
