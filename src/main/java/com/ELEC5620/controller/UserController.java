package com.ELEC5620.controller;

import com.ELEC5620.common.FaceApi;
import com.ELEC5620.entity.Users;
import com.ELEC5620.repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            System.out.println(password);
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

    @GetMapping(path="/findAllStudent")
    public List<Users> student(){return userRepository.findAllStudent();}

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

    @PostMapping(value= "/addUser",produces="application/json;charset=UTF-8")
    public String addUser(@RequestBody Map<String, Object> data){
        long id = Long.parseLong(data.get("sid").toString());
        String acc = data.get("acc").toString();
        String firstName = data.get("firstName").toString();
        String lastName = data.get("lastName").toString();
        String major = data.get("major").toString();
        Integer result = userRepository.addStudent(id,acc,firstName,lastName,major);
        System.out.println(result);
        if(result == 1){
            return "success";
        }else{
            return "fail";
        }
    }

    @PostMapping(value= "/addImg",produces="application/json;charset=UTF-8")
    public String addImg(@RequestBody Map<String, Object> data){
        String id = data.get("uid").toString();
        String img = data.get("img").toString();
        //去掉图片头，不然图片报错222203
        String[] temp;
        temp = img.split(",");
//      System.out.println(temp[1]);
        String image = temp[1];
        String name = data.get("name").toString();
        System.out.println(id+ "" +image);
        //add to baidu face bank
        FaceApi faceApi = new FaceApi();
        String returnResult = FaceApi.add(image,"elec5620",id,name);
        Gson g = new Gson();
        JsonObject obj = g.fromJson(returnResult,JsonObject.class);
        System.out.println(obj.get("error_code"));
        String error_code = obj.get("error_code").toString();
        if(Objects.equals(error_code, "0")){
            JsonObject obj_result = obj.get("result").getAsJsonObject();
            String face_token = obj_result.get("face_token").toString();
            long sid = Long.parseLong(data.get("uid").toString());
            //add info to db
            Integer result = userRepository.updateImage(sid,face_token,sid,name);
            //update img in user table
            Integer result2 = userRepository.updateImgInUser(sid);
            if(result == 1 && result2 == 1){
                return "success";}else{return "fail";}
        }else{
            return "fail";
        }
    }
}