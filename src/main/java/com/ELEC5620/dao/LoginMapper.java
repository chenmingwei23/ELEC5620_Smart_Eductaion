package com.ELEC5620.dao;

import com.ELEC5620.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper //声明DAO接口，一定不能少，也可以用@Repository，不需要实现，SpringBoot通过.xml文件自动实现
@Repository
public interface LoginMapper {

    Users selectById(long id);

    // 这里每加一个，就要在resources/mapper里加一条对应的语句
    Users selectByEmail(String email);

    int resetPassword(int id, String password);

    List<Users> selectByCompany(int companyId);

    List<Users> selectUserByPMId(int pmId);

    int insertUser(Users users);

    int updateUser(Users users);

    int deleteById(int id);
}
