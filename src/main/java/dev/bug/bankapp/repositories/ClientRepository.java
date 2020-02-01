package dev.bug.bankapp.repositories;

import dev.bug.bankapp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByName(String name);

    boolean existsByName(String name);

    List<Client> findAllByNameContains(String name);

    List<Client> findClientsByBankBankId(long bankId);
}
