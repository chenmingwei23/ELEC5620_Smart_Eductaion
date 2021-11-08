package com.ELEC5620.controller;

import com.ELEC5620.entity.Users;
import com.ELEC5620.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //getAllUsers
    @GetMapping("/getAllUsers")
    public List<Users> getAllUser(){
        return userRepository.findAll();
    }
}