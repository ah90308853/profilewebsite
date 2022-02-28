package com.website.website.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "product_and_supplier")
@Data
@NoArgsConstructor
public class ProductAndSupplier implements Serializable
{
    @EmbeddedId
    private ProductAndSupplierKey key;

    @ManyToOne()
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne()
    @MapsId("supplierId")
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToMany(mappedBy = "productAndSupplier")
    @JsonIgnore
    private Set<ProductAndSupplierInCart> productAndSupplierInCart;

    private long price;
    private long quantity;
    private String offeringName;
    private String offeringDescription;

    public ProductAndSupplier(Product product, Supplier supplier, long price, long quantity, String offeringName, String offeringDescription)
    {
        this.product = product;
        this.supplier = supplier;
        this.price = price;
        this.quantity = quantity;
        this.offeringName = offeringName;
        this.offeringDescription = offeringDescription;
    }
}
