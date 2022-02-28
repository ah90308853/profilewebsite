package com.website.website.service;

import com.website.website.entity.*;
import com.website.website.validation.UniqueClientEmail;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface Warehouse
{
    Boolean addMessage(String email, String message);
    Long addProduct(String productName, String productDescription);
    Long addSupplier(String supplierName, String supplierDescription);
    Long addClient(@UniqueClientEmail String email, String name, String address, long money);

    Product getProductById(long productId);
    Supplier getSupplierById(long supplierId);
    Client getClientById(long clientId);

    ProductAndSupplier addProductAndSupplier(long productId, long supplierId, long quantity, long price, String offeringName, String offeringDescription);
    void editProductAndSupplier(ProductAndSupplier productAndSupplier);
    void removeProductAndSupplier(ProductAndSupplierKey key);

    List<ProductAndSupplier> getProductSuppliersByProductId(long productId);
    Set<ProductAndSupplier> getSupplierProductsBySupplierId(long supplierId);


    ArrayList<Product> getAllProducts();
    ArrayList<Supplier> getAllSuppliers();
    ArrayList<Client> getAllClients();

    ProductAndSupplier getProductAndSupplierByKey(ProductAndSupplierKey key);

    void addProductAndSupplierToClientCart(ProductAndSupplierKey key, long clientId);
    void removeProductAndSupplierFromClientCart(ProductAndSupplierKey key, long clientId);

    ArrayList<ProductAndSupplierInCart> findAllByCartCartId(long id);

    boolean checkout(long clientId);
}
