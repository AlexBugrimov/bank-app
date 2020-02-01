package dev.bug.bankapp.rest;

import dev.bug.bankapp.dto.ClientDto;
import dev.bug.bankapp.exceptions.ClientNotFoundException;
import dev.bug.bankapp.model.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "clients",
        consumes = "application/json",
        produces = "application/json")
public class ClientController extends ApiController {

    @GetMapping("{name}")
    public ResponseEntity<ClientDto> getClientByName(@PathVariable String name) {
        if(clientRepository.existsByName(name)) {
            ClientDto clientDto = mapper.clientTo(clientRepository.findByName(name));
            return ResponseEntity.ok(clientDto);
        }
        throw new ClientNotFoundException(messageProvider, name);
    }

    @GetMapping("search")
    public ResponseEntity<List<ClientDto>> searchClientsByName(@RequestParam String name) {
        List<Client> clients = clientRepository.findAllByNameContains(name);
        if (clients != null || !clients.isEmpty()) {

        }
        List<ClientDto> clientsDto = mapper.clientsTo(clients);
        return clientsDto.isEmpty() ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(clientsDto);
    }
}
