package com.ELEC5620.dao;

import com.ELEC5620.entity.Demo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper //声明DAO接口，也可以用@Repository，不需要实现，SpringBoot通过.xml文件自动实现
@Repository
public interface DemoMapper {

    Demo selectById(int id);

    Demo selectByName(String username);

    Demo selectByEmail(String email);

    int insertDemo(Demo demo);

    int updateStatus(int id, int status);

    int updateHeader(int id, String headerUrl);

    int updatePassword(int id, String password);
}