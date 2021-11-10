package com.ELEC5620.dao;
import com.ELEC5620.entity.Courses;
import com.ELEC5620.entity.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CourseMapper {

    Courses selectById(int id);

    List<Courses> getAllCourses();

    List<Topic> getAllTopics(int id);

    int insertCourse(Courses courses);

    int updateCourse(Courses courses);

    int deleteById(int id);


}
