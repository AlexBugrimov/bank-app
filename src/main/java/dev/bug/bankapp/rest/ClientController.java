package dev.bug.bankapp.rest;

import dev.bug.bankapp.exceptions.ClientExistsException;
import dev.bug.bankapp.model.Client;
import dev.bug.bankapp.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        if (!clientRepository.findByName(client.getName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(clientRepository.save(client));
        }
        throw new ClientExistsException();
    }

    @GetMapping("all-names")
    public ResponseEntity<List<String>> getNamesAllClients() {
        return ResponseEntity.ok(clientRepository.findAllNamesClients());
    }

    @GetMapping("{name}")
    public ResponseEntity<Client> getClientByName(@PathVariable String name) {
        Optional<Client> client = clientRepository.findByName(name);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
