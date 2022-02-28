package com.website.website.service;

import com.website.website.entity.User;
import com.website.website.repository.UserRepository;
import com.website.website.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Validated
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private Validator validator;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException //UserDetails
    {
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException(username + " not found"));

        return user.map(UserDetailsImpl::new).get();
    }

    public boolean existsByUsername(String username)
    {
        try
        {
           loadUserByUsername(username).getUsername();
            return true;
        } catch (UsernameNotFoundException e)
        {
            System.out.println("usernamenotfound");
            return false;
        }
    }

    public boolean existsByEmail(String email)
    {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

    public boolean registerUser(@UniqueUsername @FormattedUsername String username, @FormattedPassword String password, @UniqueEmail @FormattedEmail String email)
    {
        User user = new User(username, passwordEncoder.encode(password), email);
        userRepository.save(user);
        return existsByUsername(user.getUsername());
    }
}
