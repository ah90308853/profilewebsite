package com.website.website.repository;

import com.website.website.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface SupplierRepository extends JpaRepository<Supplier, Serializable>
{}
