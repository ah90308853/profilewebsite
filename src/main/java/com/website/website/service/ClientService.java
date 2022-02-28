package com.website.website.service;

import com.website.website.entity.Client;

import java.util.List;

public interface ClientService
{
    void addClient(Client client);
    void removeClient(long clientId);
    void editClient(Client client);
    boolean existsById(long clientId);
    boolean existsByEmail(String email);
    Client getClientById(long clientId);
    List<Client> findAll();
}
