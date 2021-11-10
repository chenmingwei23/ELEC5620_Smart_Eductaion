package com.ELEC5620.dao;
import com.ELEC5620.entity.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TopicMapper {

    Topic selectById(int id);

    List<Topic> selectByCourseName(String courseName);

    int insertTopic(Topic topic);

    int updateTopic(Topic topic);

    int deleteById(int id);
}
