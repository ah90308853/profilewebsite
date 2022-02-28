package com.website.website.repository;

import com.website.website.entity.ProductAndSupplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface ProductAndSupplierRepository extends JpaRepository<ProductAndSupplier, Serializable>
{
    List<ProductAndSupplier> findAllByProductProductId(long productId);
}
