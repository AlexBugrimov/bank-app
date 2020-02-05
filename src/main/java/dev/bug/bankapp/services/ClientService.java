package dev.bug.bankapp.services;

import dev.bug.bankapp.dto.ClientDto;
import dev.bug.bankapp.exceptions.BankNotExistsException;
import dev.bug.bankapp.exceptions.ClientExistsException;
import dev.bug.bankapp.exceptions.ClientNotFoundException;
import dev.bug.bankapp.model.Bank;
import dev.bug.bankapp.model.Client;
import dev.bug.bankapp.repositories.BankRepository;
import dev.bug.bankapp.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientService extends ApiService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BankRepository bankRepository;

    public ClientDto getClientByName(String name) {
        return mapper.clientTo(
                clientRepository.findClientByName(name)
                        .orElseThrow(() -> new ClientNotFoundException(messageProvider, name))
        );
    }

    public List<ClientDto> findAllByNameContains(String name) {
        List<Client> clients = clientRepository.findAllByNameContains(name);
        return mapper.clientsTo(clients);
    }

    public void deleteClientByName(String name) {
        clientRepository.findClientByName(name)
                .ifPresent(client -> clientRepository.deleteByName(client.getName()));
    }

    public ClientDto addClient(Client client, long bankId) {
        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new BankNotExistsException(messageProvider, bankId));
        if (clientRepository.existsByName(client.getName())) {
            throw new ClientExistsException(messageProvider, client.getName());
        }
        return mapper.clientTo(clientRepository.save(client.setBank(bank)));
    }
}
