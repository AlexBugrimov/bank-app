package dev.bug.bankapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class AccountHistory {

    public enum AccountOperation {
        WITHDRAW, DEPOSIT, TRANSFER
    }

    @Id
    @GeneratedValue
    private long accountHistoryId;

    @OneToOne
    private Account account;

    @Enumerated(EnumType.STRING)
    private AccountOperation operation;

    private LocalDateTime time;

    private double balanceBefore;

    private double balanceAfter;

    @OneToOne
    private Account transferFrom;
}
