package com.website.website.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="client")
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="clientId")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Client implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id")
    private long clientId;

    @NotNull
    @Column(name = "client_email")
    private String email;
    @NotNull
    @Column(name = "client_name")
    private String name;
    @NotNull
    @Column(name = "client_address")
    private String address;
    @NotNull
    @Column(name = "client_money")
    private long money;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL) //cascade = CascadeType.ALL
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private Cart cart;

    public Client(String email, String name, String address, long money)
    {
        this.email = email;
        this.name = name;
        this.address = address;
        this.money = money;

        this.cart = new Cart();
        this.cart.setClient(this);
    }

    public Client(long id, String email, String name, String address, long money)
    {
        this.clientId = id;
        this.email = email;
        this.name = name;
        this.address = address;
        this.money = money;

        this.cart = new Cart();
        this.cart.setClient(this);
    }

    @Override
    public String toString()
    {
        return this.clientId + " " + this.email + " " + this.name + " " + this.address + " " + this.money;
    }
}
