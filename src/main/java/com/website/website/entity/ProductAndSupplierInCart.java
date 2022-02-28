package com.website.website.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_and_supplier_in_cart")
@Data
@NoArgsConstructor
public class ProductAndSupplierInCart implements Serializable
{
    @EmbeddedId
    ProductAndSupplierInCartKey productAndSupplierInCartKey;

    @ManyToOne()
    @MapsId("cartId")
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne()
    @MapsId("key")
    private ProductAndSupplier productAndSupplier;

    @Column(name = "quantity_in_cart")
    private long quantity;

    public ProductAndSupplierInCart(ProductAndSupplierInCartKey key, Cart cart, ProductAndSupplier productAndSupplier, long quantity)
    {
        this.productAndSupplierInCartKey = key;
        this.cart = cart;
        this.productAndSupplier = productAndSupplier;
        this.quantity = quantity;
    }
}
