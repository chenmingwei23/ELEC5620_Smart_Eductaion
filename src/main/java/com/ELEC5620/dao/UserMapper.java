package com.ELEC5620.dao;

import com.ELEC5620.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper //声明DAO接口，一定不能少，也可以用@Repository，不需要实现，SpringBoot通过.xml文件自动实现
@Repository
public interface UserMapper {

    Users selectById(int id);

    Users selectByAcc(String acc);

    List<Users> selectByCompany(int companyId);

    List<Users> selectUserByPMId(int pmId);

    int insertUser(Users user);

    int updateUser(Users user);

    int deleteById(int id);
}
