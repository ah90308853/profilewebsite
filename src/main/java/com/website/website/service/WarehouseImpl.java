package com.website.website.service;

import com.website.website.entity.*;
import com.website.website.validation.UniqueClientEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Validated
public class WarehouseImpl implements Warehouse
{
    @Autowired
    HireMeService hireMeService;
    @Autowired
    ProductService productService;
    @Autowired
    SupplierService supplierService;
    @Autowired
    ClientService clientService;
    @Autowired
    ProductAndSupplierService productAndSupplierService;
    @Autowired
    ProductAndSupplierInCartService productAndSupplierInCartService;

    @Override
    public Boolean addMessage(String email, String messageBody)
    {
        return hireMeService.addMessage(email, messageBody);
    }

    @Override
    public Long addProduct(String productName, String productDescription)
    {
        Product product = new Product(productName, productDescription);
        productService.addProduct(product);
        return product.getProductId();
    }


    @Override
    public Long addSupplier(String supplierName, String supplierDescription)
    {
        Supplier supplier = new Supplier(supplierName, supplierDescription);
        supplierService.addSupplier(supplier);
        return supplier.getSupplierId();
    }

    @Override
    public Long addClient(@UniqueClientEmail String email, String name, String address, long money)
    {
        Client client = new Client(email, name, address,  money);
        clientService.addClient(client);
        return client.getClientId();
    }

    @Override
    public Product getProductById(long productId)
    {
        return productService.getById(productId);
    }

    @Override
    public Supplier getSupplierById(long supplierId)
    {
        return supplierService.getById(supplierId);
    }

    @Override
    public Client getClientById(long clientId)
    {
        return clientService.getClientById(clientId);
    }

    @Override
    public ProductAndSupplier addProductAndSupplier(long productId, long supplierId, long price, long quantity, String offeringName, String offeringDescription)
    {
       Product product = productService.getById(productId);
       Supplier supplier = supplierService.getById(supplierId);
       ProductAndSupplier productAndSupplier = new ProductAndSupplier(product, supplier, price, quantity, offeringName, offeringDescription);
       ProductAndSupplierKey key = new ProductAndSupplierKey(productId, supplierId);
       productAndSupplier.setKey(key);
       productAndSupplierService.addProductAndSupplier(productAndSupplier);
       return productAndSupplier;
    }

    @Override
    public void editProductAndSupplier(ProductAndSupplier productAndSupplier)
    {
        productAndSupplierService.editProductAndSupplier(productAndSupplier);
    }

    @Override
    public void removeProductAndSupplier(ProductAndSupplierKey key)
    {
        productAndSupplierService.removeProductAndSupplier(key);
    }

    @Override
    @Transactional
    public void addProductAndSupplierToClientCart(ProductAndSupplierKey key, long clientId)
    {
        ProductAndSupplier productAndSupplier = productAndSupplierService.getProductAndSupplierByKey(key);

        Client client = clientService.getClientById(clientId);
        Cart cart = client.getCart();

        if(productAndSupplier.getQuantity() >= 1)
        {
            productAndSupplierInCartService.addProductAndSupplierInCart(cart, productAndSupplier, 1);
            productAndSupplier.setQuantity(productAndSupplier.getQuantity() - 1);
            cart.setTotal(cart.getTotal() + productAndSupplier.getPrice());
        }
    }

    @Override
    public void removeProductAndSupplierFromClientCart(ProductAndSupplierKey key, long clientId)
    {
        Client client = clientService.getClientById(clientId);
        ProductAndSupplier productAndSupplier = productAndSupplierService.getProductAndSupplierByKey(key);
        Cart cart = client.getCart();
        cart.setTotal(cart.getTotal() - productAndSupplier.getPrice());
    }

    @Override
    public ArrayList<ProductAndSupplierInCart> findAllByCartCartId(long id)
    {
        return new ArrayList<ProductAndSupplierInCart>(productAndSupplierInCartService.findAllByCartCartId(id));
    }

    @Override
    @Transactional
    public boolean checkout(long clientId)
    {
        Client client = clientService.getClientById(clientId);
        if(client.getMoney() >= client.getCart().getTotal())
        {
            client.setMoney(client.getMoney() - client.getCart().getTotal());
            client.getCart().setTotal(0);

            productAndSupplierInCartService.deleteProductAndSupplierInCartByCartId(clientId);

            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public List<ProductAndSupplier> getProductSuppliersByProductId(long productId) //map to pertinent info only for viewService
    {
        return productAndSupplierService.findAllByProductId(productId);
    }

    @Override
    public Set<ProductAndSupplier> getSupplierProductsBySupplierId(long supplierId) //map to pertinent info only for viewService
    {
        Supplier supplier = supplierService.getById(supplierId);
        return supplier.getProductAndSupplier();
    }

    @Override
    public ProductAndSupplier getProductAndSupplierByKey(ProductAndSupplierKey key)
    {
        return productAndSupplierService.getProductAndSupplierByKey(key);
    }

    @Override
    public ArrayList<Product> getAllProducts()
    {
        return new ArrayList<Product>(productService.findAll());
    }

    @Override
    public ArrayList<Supplier> getAllSuppliers()
    {
        return new ArrayList<Supplier>(supplierService.findAll());
    }

    @Override
    public ArrayList<Client> getAllClients()
    {
        return new ArrayList<Client>(clientService.findAll());
    }
}
