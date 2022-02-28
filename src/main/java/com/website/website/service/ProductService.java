package com.website.website.service;

import com.website.website.entity.Product;
import java.util.List;

public interface ProductService
{
    void addProduct(Product product);
    void removeProduct(long productId);
    void editProduct(Product product);
    boolean existsById(long productId);
    Product getById(long productId);
    List<Product> findAll();
}
