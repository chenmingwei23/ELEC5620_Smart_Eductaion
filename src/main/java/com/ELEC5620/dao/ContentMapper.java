package com.ELEC5620.dao;
import com.ELEC5620.entity.Content;
import com.ELEC5620.entity.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ContentMapper {

    List<Content> selectByTopicName(String name);

    List<Content> selectByStudentId(int id);

    void createContent(Content content);

    Content deleteContent(int id);


}
