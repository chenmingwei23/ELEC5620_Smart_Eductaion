package com.ELEC5620.service;

import com.ELEC5620.dao.DemoMapper;
import com.ELEC5620.entity.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;

@Service
public class DemoService {

    //Service依赖Dao层实现查询
    @Autowired
    private DemoMapper demoMapper;

    // 初始化后自动调用。
    @PostConstruct
    public void init() {
        System.out.println("初始化UserService");
    }

    //销毁前调用，用于释放资源。
    @PreDestroy
    public void destroy() {
        System.out.println("销毁UserService");
    }

    //模拟一个查询
    public Demo findUserById(int id) {
        return demoMapper.selectById(id);
    }

    public Map<String, Object> addDemo(Demo demo){
        demoMapper.insertDemo(demo);
        return Map.of("userMsg", "Put user success!");
    }
}
