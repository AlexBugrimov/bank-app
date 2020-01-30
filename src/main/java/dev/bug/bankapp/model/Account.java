package dev.bug.bankapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue
    private long accountId;

    @ManyToOne
    private Client client;

    private String accountNumber;

    private double balance;

    @OneToOne(mappedBy = "account")
    private AccountHistory accountHistory;

    @OneToOne(mappedBy = "transferFrom")
    private AccountHistory accountHistoryTransferFrom;

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (amount >= this.balance) {
            this.balance -= amount;
        }
    }
}
