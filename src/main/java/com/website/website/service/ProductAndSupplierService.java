package com.website.website.service;

import com.website.website.entity.ProductAndSupplier;
import com.website.website.entity.ProductAndSupplierKey;

import java.util.List;

public interface ProductAndSupplierService
{
    ProductAndSupplier addProductAndSupplier(ProductAndSupplier productAndSupplier);
    void removeProductAndSupplier(ProductAndSupplierKey key);
    void editProductAndSupplier(ProductAndSupplier productAndSupplier);
    ProductAndSupplier getProductAndSupplierByKey(ProductAndSupplierKey key);
    List<ProductAndSupplier> findAllByProductId(long productId);
}
