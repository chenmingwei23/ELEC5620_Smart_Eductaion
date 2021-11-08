package com.ELEC5620.controller;

import com.ELEC5620.entity.Users;
import com.ELEC5620.repository.UserRepository;
import com.alibaba.fastjson.JSONObject;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


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

    //@PostMapping(path = "/test" )
    @PostMapping(value= "/test",produces="application/json;charset=UTF-8")
    public String resetPassword(@RequestBody Map<String, Object> data) {
        System.out.println(data.get("acc"));
        return "success";
    }

    @CrossOrigin(origins = "http://localhost:3000/")
    @PostMapping(value= "/login",produces="application/json;charset=UTF-8")
    public List<Users> logIn(@RequestBody Map<String, Object> data) {
        List<Users> result = new ArrayList<>();
        String acc = data.get("acc").toString();
        String pwd = data.get("pwd").toString();
        if( acc == null || pwd == null ){
            Users nullUser = new Users();
            result.add(nullUser);
        }else{
            String password = userRepository.findPwdByAcc(acc);
            if(Objects.equals(password, pwd)){
                result = userRepository.findByAcc(acc);
                return result;
            }else{
                Users nullUser = new Users();
                result.add(nullUser);
                return result;
            }
        }
        return result;
    }

    @PostMapping(value= "/changePwd",produces="application/json;charset=UTF-8")
    public String changePwd(@RequestBody Map<String, Object> data){
        long uid = Long.parseLong(data.get("uid").toString());
        String pwd = data.get("pwd").toString();
        Integer result = userRepository.iniPwd(pwd,uid);
        System.out.println(result);
        if(result == 1){
        return "success";
        }else{
            return "fail";
        }
    }

    @PostMapping(value= "/getIntro",produces="application/json;charset=UTF-8")
    public List<Users> getIntro(@RequestBody Map<String, Object> data){
        String acc = data.get("acc").toString();
        List<Users> user = new ArrayList<>();
        user = userRepository.findByAcc(acc);
        return user;
    }
}