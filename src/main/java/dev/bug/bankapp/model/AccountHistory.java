package dev.bug.bankapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class AccountHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountHistoryId;

    @ManyToOne
    private Account account;

    @Enumerated(EnumType.STRING)
    private AccountOperation operation;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime time;

    private double balanceBefore;

    private double balanceAfter;

    @ManyToOne
    private Account transferFrom;


    public AccountHistory(AccountOperation operation) {
       this.operation = operation;
    }
}
