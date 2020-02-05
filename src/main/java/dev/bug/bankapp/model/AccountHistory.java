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
    @GeneratedValue
    private long accountHistoryId;

    @ManyToOne
    private Account account;

    @Enumerated(EnumType.STRING)
    private AccountOperation operation;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime time;

    private double balanceBefore;

    private double balanceAfter;

    @ManyToOne
    private Account transferFrom;

    @SneakyThrows
    public AccountHistory doJoinPoint(ProceedingJoinPoint joinPoint) {
        joinPoint.proceed();
        return this;
    }
}
