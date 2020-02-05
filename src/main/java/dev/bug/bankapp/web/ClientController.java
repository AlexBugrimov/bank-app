package dev.bug.bankapp.web;

import dev.bug.bankapp.dto.AccountDto;
import dev.bug.bankapp.dto.ClientDto;
import dev.bug.bankapp.model.Client;
import dev.bug.bankapp.services.AccountService;
import dev.bug.bankapp.services.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("Clients")
@RestController
@RequestMapping(value = "clients",
        produces = "application/json",
        consumes = "application/json")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;

    @GetMapping("{name}")
    @ApiOperation(value = "Получить клиента по имени", response = ClientDto.class)
    public ResponseEntity<ClientDto> getClient(
            @ApiParam(value = "Имя клиента", required = true)
            @PathVariable String name) {
        ClientDto clientDto = clientService.getClientByName(name);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clientDto);
    }

    @GetMapping
    @ApiOperation(value = "Получить клиентов с именем", response = List.class)
    public ResponseEntity<List<ClientDto>> getClientsByName(
            @ApiParam(value = "Часть имени клиента", required = true)
            @RequestParam String name) {
        List<ClientDto> clientsDto = clientService.findAllByNameContains(name);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clientsDto);
    }

    @GetMapping("{name}/accounts")
    @ApiOperation(value = "Получить список счетов клиента по его имени", response = List.class)
    public ResponseEntity<List<AccountDto>> getAccountsByClientName(
            @ApiParam(value = "Имя клиента", required = true)
            @PathVariable String name) {
        List<AccountDto> accountsDto = accountService.getAccountsByClientName(name);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsDto);
    }

    @DeleteMapping("{name}")
    @ApiOperation(value = "Удаление клиента")
    public ResponseEntity<Void> deleteClient(
            @ApiParam(value = "Имя клиента", required = true)
            @PathVariable String name) {
        clientService.deleteClientByName(name);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PostMapping
    @ApiOperation(value = "Добавить клиента", response = ClientDto.class)
    public ResponseEntity<ClientDto> addClient(@RequestBody Client client, @PathVariable long bankId) {
        ClientDto clientDto = clientService.addClient(client, bankId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clientDto);
    }
}
