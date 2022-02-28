package com.website.website.service;

import com.website.website.entity.ProductAndSupplier;
import com.website.website.entity.ProductAndSupplierKey;
import com.website.website.repository.ProductAndSupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductAndSupplierServiceImpl implements ProductAndSupplierService
{
    @Autowired
    ProductAndSupplierRepository productAndSupplierRepository;

    @Override
    public ProductAndSupplier addProductAndSupplier(ProductAndSupplier productAndSupplier)
    {
        productAndSupplier.getKey();
        return productAndSupplierRepository.save(productAndSupplier);
    }

    @Override
    public void removeProductAndSupplier(ProductAndSupplierKey key)
    {
        if(existsById(key))
        {
            productAndSupplierRepository.deleteById(key);
        }
    }

    @Override
    public void editProductAndSupplier(ProductAndSupplier productAndSupplier)
    {
        if(existsById(productAndSupplier.getKey())) {
            productAndSupplierRepository.save(productAndSupplier);
        }
    }

    @Override
    public ProductAndSupplier getProductAndSupplierByKey(ProductAndSupplierKey key)
    {
        return productAndSupplierRepository.getById(key);
    }

    @Override
    public List<ProductAndSupplier> findAllByProductId(long productId)
    {
        return productAndSupplierRepository.findAllByProductProductId(productId);
    }

    public boolean existsById(ProductAndSupplierKey key)
    {
        return productAndSupplierRepository.existsById(key);
    }
}
