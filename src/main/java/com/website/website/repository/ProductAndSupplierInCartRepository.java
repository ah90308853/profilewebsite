package com.website.website.repository;

import com.website.website.entity.ProductAndSupplierInCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface ProductAndSupplierInCartRepository extends JpaRepository<ProductAndSupplierInCart, Serializable>
{
    List<ProductAndSupplierInCart> findAllByCartCartId(long cartId);
    void deleteAllByCartCartId(long cartId);

}
