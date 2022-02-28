package com.website.website.service;

import com.website.website.entity.Cart;
import com.website.website.entity.ProductAndSupplier;
import com.website.website.entity.ProductAndSupplierInCart;
import com.website.website.entity.ProductAndSupplierInCartKey;
import com.website.website.repository.ProductAndSupplierInCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductAndSupplierInCartServiceImpl implements ProductAndSupplierInCartService
{
    @Autowired
    ProductAndSupplierInCartRepository productAndSupplierInCartRepository;

    @Override
    public void addProductAndSupplierInCart(Cart cart, ProductAndSupplier productAndSupplier, long quantity)
    {
        ProductAndSupplierInCartKey productAndSupplierInCartKey = new ProductAndSupplierInCartKey(productAndSupplier.getKey(), cart.getCartId());

        if(!productAndSupplierInCartRepository.existsById(productAndSupplierInCartKey))
        {
            ProductAndSupplierInCart productAndSupplierInCart = new ProductAndSupplierInCart(productAndSupplierInCartKey, cart, productAndSupplier, quantity);
            productAndSupplierInCartRepository.save(productAndSupplierInCart);
        }
        else
        {
            ProductAndSupplierInCart productAndSupplierInCart = productAndSupplierInCartRepository.getById(productAndSupplierInCartKey);
            productAndSupplierInCart.setQuantity(productAndSupplierInCart.getQuantity() + 1);
        }

        productAndSupplierInCartRepository.findAllByCartCartId(cart.getCartId()).forEach((productAndSupplierInCart) -> {System.out.println(productAndSupplierInCart.getProductAndSupplier().getProduct());});


    }

    @Override
    public void editProductAndSupplierInCart(ProductAndSupplierInCart productAndSupplierInCart)
    {
        if(productAndSupplierInCartRepository.existsById(productAndSupplierInCart.getProductAndSupplierInCartKey()))
        {
            productAndSupplierInCartRepository.save(productAndSupplierInCart);
        }
    }

    @Override
    public void removeProductAndSupplierInCart(ProductAndSupplierInCartKey key)
    {
        if(productAndSupplierInCartRepository.existsById(key))
        {
            productAndSupplierInCartRepository.deleteById(key);
        }
    }

    @Override
    public void deleteProductAndSupplierInCartByCartId(long cartId)
    {
        productAndSupplierInCartRepository.deleteAllByCartCartId(cartId);
    }

    @Override
    public List<ProductAndSupplierInCart> findAllByCartCartId(long id)
    {
        return productAndSupplierInCartRepository.findAllByCartCartId(id);
    }
}
