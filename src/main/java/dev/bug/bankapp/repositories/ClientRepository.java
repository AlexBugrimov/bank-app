package dev.bug.bankapp.repositories;

import dev.bug.bankapp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByName(String name);

    Optional<Client> findClientByName(String name);

    List<Client> findAllByNameContains(String name);

    List<Client> findClientsByBankBankId(long bankId);

    void deleteByName(String name);

    boolean existsByName(String name);
}
