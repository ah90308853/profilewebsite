package com.website.website.service;

import com.website.website.entity.Message;
import com.website.website.repository.HireMeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HireMeServiceImpl implements HireMeService
{
    @Autowired
    HireMeRepository hireMeRepository;

    @Override
    public boolean addMessage(String email, String messageBody)
    {
        if (email == null || messageBody == null)
        {
            return false;
        }

        Message message = new Message(email, messageBody);
        try
        {
            hireMeRepository.save(message);
            return true;
        }
        catch (IllegalArgumentException e)
        {
            return false;
        }
    }
}
