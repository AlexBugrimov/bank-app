package dev.bug.bankapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Bank {

    @Id
    @GeneratedValue
    private long bankId;

    @OneToMany(mappedBy = "bank")
    private List<Client> clients = new ArrayList<>();

    public void addClient(Client client) {
        this.clients.add(client);
    }
}
