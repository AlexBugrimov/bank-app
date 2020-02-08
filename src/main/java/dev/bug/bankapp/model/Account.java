package dev.bug.bankapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountId;

    @ManyToOne
    private Client client;

    private String accountNumber;

    private double balance;

    @OneToMany(mappedBy = "account")
    private List<AccountHistory> accountHistories;

    @OneToMany(mappedBy = "transferFrom")
    private List<AccountHistory> accountHistoriesTransferFrom;

    public Account deposit(double amount) {
        this.balance += amount;
        return this;
    }

    public Account withdraw(double amount) {
        this.balance -= amount;
        return this;
    }
}
