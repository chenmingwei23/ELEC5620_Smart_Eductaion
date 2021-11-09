package com.ELEC5620.dao;
import com.ELEC5620.entity.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TopicMapper {

    Topic selectById(int id);

    int insertTopic(Topic topic);

    int updateTopic(Topic topic);

    int deleteById(int id);
}
