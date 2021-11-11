package com.ELEC5620.controller;

import com.ELEC5620.dao.AttendanceMapper;
import com.ELEC5620.dao.ContentMapper;
import com.ELEC5620.dao.CourseMapper;
import com.ELEC5620.dao.TopicMapper;
import com.ELEC5620.entity.*;
import com.ELEC5620.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/profile")
@CrossOrigin(origins = "http://localhost:3000/")
public class    UserProfileController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CourseMapper courseMapper;

    @GetMapping(value= "/postUserProfile",produces="application/json;charset=UTF-8")
    public List<Users> getUserProfile(String userName) {
        List<Users> users = userRepository.findByAcc(userName);
        return users;
    }


    @PostMapping(value= "/postUserProfile",produces="application/json;charset=UTF-8")
    public int postUserProfile(String userName, String email, String firstName, String lastName, String address, String postCode, String country) {
        return userRepository.updateUser(userName, email, firstName, lastName,  address, postCode, country);
    }

}
