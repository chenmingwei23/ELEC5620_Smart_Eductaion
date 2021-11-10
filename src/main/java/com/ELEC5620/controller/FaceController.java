package com.ELEC5620.controller;

import com.ELEC5620.common.FaceApi;
import com.ELEC5620.entity.Users;
import com.baidu.ai.aip.utils.Base64Util;
import com.baidu.aip.face.AipFace;
import org.springframework.web.bind.annotation.*;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

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
        //byte[] bytes = Base64Util.base64ToImgByteArray(data.get("imagebase64").toString());
        String img = data.get("imagebase64").toString();
        String[] temp;
        temp = img.split(",");
        String img_input = temp[1];
        //System.out.println(img_input);
        FaceApi faceApi = new FaceApi();
        String returnResult = FaceApi.faceSearch(img_input,"elec5620");
//        System.out.println(returnResult);
        return "success";
    }

}
