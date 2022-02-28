package com.website.website.service;

import com.website.website.entity.Supplier;
import com.website.website.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService
{
    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public void addSupplier(Supplier supplier)
    {
        supplierRepository.save(supplier);
    }

    @Override
    public void removeSupplier(long supplierId)
    {
        if (existsById(supplierId))
        {
            supplierRepository.delete(supplierRepository.getById(supplierId));
        }
    }

    @Override
    public void editSupplier(Supplier supplier)
    {
        if (existsById(supplier.getSupplierId()))
        {
            supplierRepository.save(supplier);
        }
    }

    @Override
    public boolean existsById(long supplierId)
    {
        return supplierRepository.existsById(supplierId);
    }

    @Override
    public Supplier getById(long supplierId)
    {
        if(existsById(supplierId))
        {
            return supplierRepository.getById(supplierId);
        }
        return null;
    }

    @Override
    public List<Supplier> findAll()
    {
        return supplierRepository.findAll();
    }
}
