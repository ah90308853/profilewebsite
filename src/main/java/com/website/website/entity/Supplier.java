package com.website.website.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "supplier")
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="supplierId")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Supplier implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "supplier_id")
    private long supplierId;

    @NotNull
    @Column(name = "supplier_name")
    private String supplierName;
    @NotNull
    @Column(name = "supplier_Description")
    private String supplierDescription;

    @OneToMany(mappedBy = "supplier")
    @JsonIgnore
    Set<ProductAndSupplier> productAndSupplier;

    public Supplier(String supplierName, String supplierDescription)
    {
        this.supplierName = supplierName;
        this.supplierDescription = supplierDescription;
    }
}
