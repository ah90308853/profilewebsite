package com.website.website.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@JsonIgnoreType
public class ProductAndSupplierKey implements Serializable
{
    @Column(name = "product_id")
    long productId;

    @Column(name = "supplier_id")
    long supplierId;

    public ProductAndSupplierKey(long productId, long supplierId)
    {
        this.productId = productId;
        this.supplierId = supplierId;
    }

    @Override
    public boolean equals(Object o)
    {
        ProductAndSupplierKey productAndSupplierKey = (ProductAndSupplierKey)o;
        if(productId == productAndSupplierKey.productId && supplierId == productAndSupplierKey.supplierId)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    @Override
    public int hashCode()
    {
        return Objects.hash(productId, supplierId);
    }
}
