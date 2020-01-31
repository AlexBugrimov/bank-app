package dev.bug.bankapp.repositories;

import dev.bug.bankapp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByName(String name);

    @Query("select c.name from Client c")
    List<String> findAllNamesClients();

    List<Client> findAllByNameContains(String name);
}
