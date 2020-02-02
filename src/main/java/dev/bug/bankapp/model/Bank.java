package dev.bug.bankapp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "Bank", description = "Банк")
public class Bank {

    @Id
    @GeneratedValue
    @ApiModelProperty("Идентефикатор банка")
    private long bankId;

    @OneToMany(mappedBy = "bank")
    @ApiModelProperty("Список клиентов")
    private List<Client> clients = new ArrayList<>();

    public Bank addClient(Client client) {
        this.clients.add(client);
        return this;
    }
}
