package com.ELEC5620.repository;

import com.ELEC5620.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<Users, Long> {
    //这个接口继承了基本的CRUD方法...

    @Query("select t from Users t where t.account = ?1")
    public List<Users> findByAcc(String acc);

    @Query("select t.password from Users t where t.account = ?1")
    public String findPwdByAcc(String acc);

    @Query("select s from Users s where s.role = 0")
    public List<Users> findAllStudent();

    @Modifying
    @Transactional
    @Query(value = "insert into user(id,acc,first_name,last_name,major,role,pwd,gender) values(?1,?2,?3,?4,?5,0,123,0)",nativeQuery = true)
    public Integer addStudent(long id,String acc,String firstName, String lastName, String major);

    @Modifying
    @Transactional
    @Query("update Users u set u.password = ?1,u.changPwd = 1 where u.id = ?2")
    public Integer iniPwd(String pwd,Long id);

    @Modifying
    @Transactional
    @Query(value = "insert into image(id,face_token,uid,uname) values (?1,?2,?3,?4)", nativeQuery = true)
    public Integer updateImage(Long id,String face_token, Long u_id, String uname);

    @Modifying
    @Transactional
    @Query(value = "update user set user.image = 1 where id = ?1", nativeQuery = true)
    public Integer updateImgInUser(Long uid);
}
