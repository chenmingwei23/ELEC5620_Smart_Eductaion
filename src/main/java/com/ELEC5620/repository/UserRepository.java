package com.ELEC5620.repository;

import com.ELEC5620.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<Users, Long> {
    //这个接口继承了基本的CRUD方法...
}
