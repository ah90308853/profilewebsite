package com.website.website.repository;

import com.website.website.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Serializable>
{
    Optional<Client> findByEmail(String email);
}
