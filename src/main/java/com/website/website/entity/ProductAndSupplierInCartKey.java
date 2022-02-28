package com.website.website.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@JsonIgnoreType
public class ProductAndSupplierInCartKey implements Serializable
{
    ProductAndSupplierKey key;

    @Column(name = "cart_id")
    private long cartId;

    public ProductAndSupplierInCartKey(ProductAndSupplierKey key, long cartId)
    {
        this.key = key;
        this.cartId = cartId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductAndSupplierInCartKey that = (ProductAndSupplierInCartKey) o;
        return cartId == that.cartId && Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, cartId);
    }
}
