package dev.bug.bankapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue
    private long clientId;

    private String name;

    @OneToMany(mappedBy = "client")
    private List<Account> accounts = new ArrayList<>();

    @ManyToOne
    private Bank bank;

    public Client(String name) {
        this.name = name;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }
}
