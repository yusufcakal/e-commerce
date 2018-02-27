package com.yusufcakal.ecommerce.controller;

import com.yusufcakal.ecommerce.model.User;
import com.yusufcakal.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/user")
public class BaseController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity<User> register(@RequestBody User user) throws EntityNotFoundException {

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
