package com.website.website.service;

import com.website.website.entity.Product;
import com.website.website.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService
{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addProduct(Product product)
    {
        productRepository.save(product);
    }

    @Override
    public void removeProduct(long productId)
    {
        if (existsById(productId))
        {
            productRepository.delete(productRepository.getById(productId));
        }
    }

    @Override
    public void editProduct(Product product)
    {
        if(existsById(product.getProductId()))
        {
            productRepository.save(product);
        }
    }

    @Override
    public boolean existsById(long productId)
    {
        return productRepository.existsById(productId);
    }

    @Override
    public Product getById(long productId)
    {
        if(existsById(productId))
        {
            return productRepository.getById(productId);
        }
        return null;
    }

    @Override
    public List<Product> findAll()
    {
        return productRepository.findAll();
    }
}
