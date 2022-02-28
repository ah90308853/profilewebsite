package com.website.website.service;

import com.website.website.entity.Cart;
import com.website.website.entity.ProductAndSupplier;
import com.website.website.entity.ProductAndSupplierInCart;
import com.website.website.entity.ProductAndSupplierInCartKey;

import java.util.List;

public interface ProductAndSupplierInCartService
{
    void addProductAndSupplierInCart(Cart cart, ProductAndSupplier productAndSupplier, long quantity);
    void editProductAndSupplierInCart(ProductAndSupplierInCart productAndSupplierInCart);
    void removeProductAndSupplierInCart(ProductAndSupplierInCartKey key);

    void deleteProductAndSupplierInCartByCartId(long cartId);

    List<ProductAndSupplierInCart> findAllByCartCartId(long id);
}
