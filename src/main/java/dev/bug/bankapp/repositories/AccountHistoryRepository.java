package dev.bug.bankapp.repositories;

import dev.bug.bankapp.model.AccountHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountHistoryRepository extends JpaRepository<AccountHistory, Long> {
}
