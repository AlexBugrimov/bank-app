package dev.bug.bankapp.model;

import dev.bug.bankapp.exceptions.NotEnoughFundsException;
import dev.bug.bankapp.utils.ErrorMessageProvider;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

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
