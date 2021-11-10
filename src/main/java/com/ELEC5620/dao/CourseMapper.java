package com.ELEC5620.dao;
import com.ELEC5620.entity.Courses;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CourseMapper {

    Courses selectById(int id);

    int insertCourse(Courses courses);

    int updateCourse(Courses courses);

    int deleteById(int id);


}
