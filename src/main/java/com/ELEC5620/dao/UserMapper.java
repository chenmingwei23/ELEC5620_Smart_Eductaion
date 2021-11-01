package com.ELEC5620.dao;

import com.ELEC5620.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper //声明DAO接口，一定不能少，也可以用@Repository，不需要实现，SpringBoot通过.xml文件自动实现
@Repository
public interface UserMapper {

    User selectById(int id);

    // 这里每加一个，就要在resources/mapper里加一条对应的语句
    User selectByEmail(String email);

    int resetPassword(int id, String password);

    List<User> selectByCompany(int companyId);

    List<User> selectUserByPMId(int pmId);

    int insertUser(User user);

    int updateUser(User user);

    int deleteById(int id);
}
