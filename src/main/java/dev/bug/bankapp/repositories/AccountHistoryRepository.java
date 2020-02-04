package dev.bug.bankapp.repositories;

import dev.bug.bankapp.model.AccountHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AccountHistoryRepository extends JpaRepository<AccountHistory, Long> {

    List<AccountHistory> findAllByTimeBetween(LocalDateTime from, LocalDateTime to);
}
