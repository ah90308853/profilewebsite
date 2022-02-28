package com.website.website.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "cart")
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="cartId")
public class Cart implements Serializable
{
    @Id
    @Column(name = "cart_id")
    private long cartId;

    @Column(name = "cart_total")
    private long total;

    @OneToOne()
    @MapsId
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client client;

    @OneToMany(mappedBy = "cart")
    @JsonIgnore
    private Set<ProductAndSupplierInCart>  productAndSupplierInCart;

}
