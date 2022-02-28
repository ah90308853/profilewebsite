package com.website.website.controller;

import com.website.website.entity.*;
import com.website.website.service.Warehouse;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://ngcode.s3-website-us-east-1.amazonaws.com")
public class WarehouseController
{
    @Autowired
    Warehouse warehouse;

    @PostMapping(value = "/newmessage",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> addMessage(@RequestBody Message message)
    {
        Boolean result = warehouse.addMessage(message.getEmail(), message.getMessage());
        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/newclient",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> addClient(@RequestBody Client client)
    {
        System.out.println("hit /newclient");
        System.out.println("payload read as " + client.getName());
        Long clientId = warehouse.addClient(client.getEmail(), client.getName(), client.getAddress(), client.getMoney()); //use validation on warehouse service params
        return new ResponseEntity<Long>(clientId, HttpStatus.OK);
    }

    @PostMapping(value = "/newproduct",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> addProduct(@RequestBody Product product)
    {
        Long productId = warehouse.addProduct(product.getProductName(), product.getProductDescription());
        return new ResponseEntity<Long>(productId, HttpStatus.OK);
    }

    @PostMapping(value = "/newsupplier",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> addSupplier(@RequestBody Supplier supplier)
    {
        Long supplierId = warehouse.addSupplier(supplier.getSupplierName(), supplier.getSupplierDescription());
        return new ResponseEntity<Long>(supplierId, HttpStatus.OK);
    }

    @PostMapping(value = "/newproductandsupplier",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> addProductAndSupplier(@RequestBody ProductAndSupplier productAndSupplier)
    {
        Boolean creationSuccess = false;

        ProductAndSupplier databaseProductAndSupplier = warehouse.addProductAndSupplier(productAndSupplier.getProduct().getProductId(),
                productAndSupplier.getSupplier().getSupplierId(),
                productAndSupplier.getPrice(),
                productAndSupplier.getQuantity(),
                productAndSupplier.getOfferingName(),
                productAndSupplier.getOfferingDescription());

        if (databaseProductAndSupplier != null)
        {
            creationSuccess = true;
        }

        return new ResponseEntity<Boolean>(creationSuccess, HttpStatus.OK);
    }

    @GetMapping(value = "/products",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Product>> getAvailableProducts()
    {
        return new ResponseEntity<ArrayList<Product>>(warehouse.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping(value = "/suppliers",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Supplier>> getAvailableSuppliers()
    {
        return new ResponseEntity<ArrayList<Supplier>>(warehouse.getAllSuppliers(), HttpStatus.OK);
    }

    @PostMapping(value = "/productofferings",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<ProductAndSupplier>> getProductAndSupplierByProductId(@RequestBody long productId)
    {
        return new ResponseEntity<ArrayList<ProductAndSupplier>>((ArrayList<ProductAndSupplier>) warehouse.getProductSuppliersByProductId(productId), HttpStatus.OK);
    }

    @GetMapping(value = "/supplierofferings",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ProductAndSupplier>> getProductAndSupplierBySupplierId(@RequestParam Long id)
    {
        return new ResponseEntity<Set<ProductAndSupplier>>(warehouse.getSupplierProductsBySupplierId(id), HttpStatus.OK);
    }

    @GetMapping(value = "/clients",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Client>> getAllClients()
    {
        return new ResponseEntity<ArrayList<Client>>(warehouse.getAllClients(), HttpStatus.OK);
    }

    @PostMapping(value = "/client",
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> getClientById(@RequestBody long id)
    {
        return new ResponseEntity<Client>(warehouse.getClientById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/cart",
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<ProductAndSupplierInCart>> getCartById(@RequestBody long id)
    {
        return new ResponseEntity<ArrayList<ProductAndSupplierInCart>>(warehouse.findAllByCartCartId(id), HttpStatus.OK);
    }

    @PostMapping(value = "/product",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProductById(@RequestBody long id)
    {
        return new ResponseEntity<Product>(warehouse.getProductById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/supplier",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Supplier> getSupplierById(@RequestBody long id)
    {
        return new ResponseEntity<Supplier>(warehouse.getSupplierById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/addtocart",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addProductAndSupplierToCart(@RequestBody ArrayList<Long> ids)
    {
        ProductAndSupplierKey key = new ProductAndSupplierKey(ids.get(1), ids.get(2));
        warehouse.addProductAndSupplierToClientCart(key, ids.get(0));
    }

    @PostMapping(value = "/checkout",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> checkout(@RequestBody long id)
    {
        return new ResponseEntity<Boolean>(warehouse.checkout(id), HttpStatus.OK);
    }




}
