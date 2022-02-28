package com.website.website.controller;
import com.website.website.entity.ApiError;
import com.website.website.entity.User;
import com.website.website.service.UserDetailsImpl;
import com.website.website.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@CrossOrigin(origins = "http://ngcode.s3-website-us-east-1.amazonaws.com")
public class UserController
{
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @GetMapping(value = "/getusername",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUsername(UserDetailsImpl user)
    {
       UserDetailsImpl databaseLoggedInUser = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       System.out.println(databaseLoggedInUser.getUsername());
       return new ResponseEntity<String>(databaseLoggedInUser.getUsername(), HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public boolean authenticateUser()
    {
        return true;
    }

    @PostMapping(value = "/register",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> registerUser(@RequestBody User user)
    {
        userDetailsService.registerUser(user.getUsername(), user.getPassword(), user.getEmail());
        return new ResponseEntity<Boolean>(userDetailsService.existsByUsername(user.getUsername()), HttpStatus.CREATED);
    }

    @PostMapping(value = "/isusernametaken",
    consumes = MediaType.TEXT_PLAIN_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> isUsernameUnique (@RequestBody String username)
    {
        return new ResponseEntity<Boolean>(userDetailsService.existsByUsername(username), HttpStatus.CREATED);
    }

    @PostMapping(value = "/isemailtaken",
    consumes = MediaType.TEXT_PLAIN_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> isEmailUnique (@RequestBody String email)
    {
        return new ResponseEntity<Boolean>(userDetailsService.existsByEmail(email), HttpStatus.CREATED);
    }
}
