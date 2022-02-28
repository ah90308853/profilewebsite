package com.website.website.repository;

import com.website.website.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface CartRepository extends JpaRepository<Cart, Serializable>
{}
