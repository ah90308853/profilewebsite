package com.website.website.service;

import com.website.website.entity.Client;
import com.website.website.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService
{
    @Autowired
    private ClientRepository clientRepository;


    @Override
    public void addClient(Client client)
    {
        clientRepository.save(client).getClientId();
    }

    @Override
    public void removeClient(long clientId)
    {
        if (existsById(clientId))
        {
            clientRepository.deleteById(clientId);
        }
    }

    @Override
    public void editClient(Client client)
    {
        if (existsById(client.getClientId()))
        {
            clientRepository.save(client);
        }
    }

    @Override
    public boolean existsById(long clientId)
    {
        return clientRepository.existsById(clientId);
    }

    @Override
    public boolean existsByEmail(String email)
    {
        Optional<Client> client = clientRepository.findByEmail(email);
        return client.isPresent();
    }

    @Override
    public Client getClientById(long clientId)
    {
        if(existsById(clientId))
        {
            return clientRepository.getById(clientId);
        }
        return null;
    }

    @Override
    public List<Client> findAll()
    {
        return clientRepository.findAll();
    }
}
