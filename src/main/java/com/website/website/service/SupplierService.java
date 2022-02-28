package com.website.website.service;

import com.website.website.entity.Supplier;

import java.util.List;

public interface SupplierService
{
    void addSupplier(Supplier supplier);
    void removeSupplier(long supplierId);
    void editSupplier(Supplier supplier);
    boolean existsById(long supplierId);
    Supplier getById(long supplierId);
    List<Supplier> findAll();
}
