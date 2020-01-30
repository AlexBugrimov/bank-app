package dev.bug.bankapp.repositories;

import dev.bug.bankapp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
