package com.ELEC5620.common;

import java.util.HashMap;
import java.util.Map;
import com.baidu.aip.face.AipFace;
import org.springframework.stereotype.Component;

@Component
public class FaceRegister {

    public String APP_ID = "25124099";
    public String API_KEY = "UwaZ3Xj5HZQB3DNk1aeVfI5G";
    public String SECRET_KEY = "TIKz3KsqFLgfUu4RAOWBzjGZ0FB4GMqO";
    public String GROUD_LIST = "elec5620";

    public static String add(String image,String group_id, String user_id, String user_name){
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", image);
            map.put("group_id", "elec5620"); //默认elec5620
            map.put("user_id", user_id);
            map.put("user_info", user_name);
            map.put("liveness_control", "NONE");
            map.put("image_type", "BASE64");
            map.put("quality_control", "LOW");

            String param = com.baidu.ai.aip.utils.GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            GetAuth getAuth = new GetAuth();
            String accessToken = getAuth.getToken("UwaZ3Xj5HZQB3DNk1aeVfI5G","TIKz3KsqFLgfUu4RAOWBzjGZ0FB4GMqO");

            String result = com.baidu.ai.aip.utils.HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
