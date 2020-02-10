package dev.bug.bankapp.services;

import dev.bug.bankapp.dto.BankDto;
import dev.bug.bankapp.exceptions.BankNotExistsException;
import dev.bug.bankapp.exceptions.ClientsNotFoundException;
import dev.bug.bankapp.model.Bank;
import dev.bug.bankapp.model.Client;
import dev.bug.bankapp.repositories.BankRepository;
import dev.bug.bankapp.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class BankService extends ApiService {

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private ClientRepository clientRepository;

    public BankDto createBank(Bank bank) {
        return mapper.bankTo(bankRepository.save(bank));
    }

    public List<BankDto> getBanks() {
        return mapper.banksTo(bankRepository.findAll());
    }

    public List<String> getClientNames(long bankId) {
        if (!bankRepository.existsById(bankId)) {
            throw new BankNotExistsException(messageProvider, bankId);
        }
        return clientRepository.findClientsByBankBankId(bankId)
                .stream()
                .filter(Objects::nonNull)
                .map(Client::getName)
                .collect(Collectors.toList());
    }
}
