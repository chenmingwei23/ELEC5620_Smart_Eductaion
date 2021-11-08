package com.ELEC5620.controller;

import com.ELEC5620.entity.Users;
import com.ELEC5620.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


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


    @RequestMapping(path = "/test", method = RequestMethod.POST)
    public void test(@RequestBody Map<String, Object> payload) {
        System.out.println(payload.get("test"));
    }
}