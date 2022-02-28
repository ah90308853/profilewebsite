package com.website.website.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="productId")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private long productId;

    @NotNull
    @Column(name = "product_name")
    private String productName;
    @NotNull
    @Column(name = "product_description")
    private String productDescription;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<ProductAndSupplier> productAndSupplier;

    public Product(String productName, String productDescription)
    {
        this.productName = productName;
        this.productDescription = productDescription;
    }

    @Override
    public String toString()
    {
        return this.productName + " " + this.productDescription + this.productId;
    }
}
