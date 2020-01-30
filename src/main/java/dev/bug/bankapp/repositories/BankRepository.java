package dev.bug.bankapp.repositories;

import dev.bug.bankapp.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
}
