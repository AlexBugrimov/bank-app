package dev.bug.bankapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@ApiModel(value = "Client", description = "Клиент")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long clientId;

    private String name;

    @OneToMany(mappedBy = "client")
    private List<Account> accounts = new ArrayList<>();

    @ManyToOne
    @JsonIgnoreProperties("bank_id")
    private Bank bank;

    public Client(String name) {
        this.name = name;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }
}
