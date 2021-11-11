package com.ELEC5620.controller;

import com.ELEC5620.common.FaceApi;
import com.ELEC5620.entity.Users;
import com.baidu.ai.api.utils.Base64Util;
import com.baidu.aip.face.AipFace;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.*;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/face")
@CrossOrigin(origins = "http://localhost:3001/")
public class FaceController {

    @GetMapping("/getTest")
    public String getTest(){
        return "success";
    }

    @PostMapping(value= "/postTest",produces="application/json;charset=UTF-8")
    public String postTest(@RequestBody Map<String, Object> data) {
        System.out.println(data.get("imagebase64"));
        return "success";
    }

    @PostMapping(value= "/postFace",produces="application/json;charset=UTF-8")
    public String getFaceFromFront(@RequestBody Map<String, Object> data) throws IOException {
        // byte[] bytes = Base64Util.base64ToImgByteArray(data.get("imagebase64").toString());
        String img = data.get("img").toString();
        String[] temp;
        temp = img.split(",");
        String img_input = temp[1];
        System.out.println(img_input);
        FaceApi faceApi = new FaceApi();
        String returnResult = FaceApi.faceSearch(img_input,"elec5620");
//        System.out.println(returnResult);
        Gson g = new Gson();
        JsonObject obj = g.fromJson(returnResult,JsonObject.class);
        System.out.println(obj.get("error_code"));
        String error_code = obj.get("error_code").toString();
        //JsonObject user_List = obj_result.get("user_List").getAsJsonObject();
            //String user_info = user_List.get("user_info").toString();
            //System.out.println(user_info);
        if(error_code.equals("0")){
        return obj.get("result").getAsJsonObject().toString();}else{return "error";}

    }

}
