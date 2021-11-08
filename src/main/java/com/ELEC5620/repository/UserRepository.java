package com.ELEC5620.repository;

import com.ELEC5620.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<Users, Long> {
    //这个接口继承了基本的CRUD方法...

    @Query("select t from Users t where t.account = ?1")
    public List<Users> findByAcc(String acc);

    @Query("select t.password from Users t where t.account = ?1")
    public String findPwdByAcc(String acc);

}
