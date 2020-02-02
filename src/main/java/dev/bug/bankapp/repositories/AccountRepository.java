package dev.bug.bankapp.repositories;

import dev.bug.bankapp.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByClientName(String name);

    boolean existsByAccountNumber(String accountNumber);

    Account findByAccountNumber(String accountNumber);

    void deleteByAccountNumber(String accountNumber);
}
