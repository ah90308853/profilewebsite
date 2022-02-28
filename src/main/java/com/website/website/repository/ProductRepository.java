package com.website.website.repository;

import com.website.website.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface ProductRepository extends JpaRepository<Product, Serializable>
{}
