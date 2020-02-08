package dev.bug.bankapp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@ApiModel(value = "Bank", description = "Банк")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Идентефикатор банка")
    private long bankId;

    @ApiModelProperty("Название банка")
    private String name;

    @OneToMany(mappedBy = "bank")
    @ApiModelProperty("Список клиентов")
    private List<Client> clients = new ArrayList<>();

    public Bank(String name) {
        this.name = name;
    }

    public Bank addClient(Client client) {
        this.clients.add(client);
        return this;
    }
}
