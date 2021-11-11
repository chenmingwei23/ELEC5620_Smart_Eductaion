package com.ELEC5620.controller;

import com.ELEC5620.dao.ContentMapper;
import com.ELEC5620.dao.CourseMapper;
import com.ELEC5620.dao.TopicMapper;
import com.ELEC5620.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/discussion")
@CrossOrigin(origins = "http://localhost:3000/")
public class discussionController {

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    TopicMapper topicMapper;

    @Autowired
    ContentMapper contentMapper;

    @GetMapping("/getAllCourses")
    public List<Courses> getAllCourses(){
        List<Courses> all = courseMapper.getAllCourses();
        return all;
    }

    @GetMapping(value= "/course",produces="application/json;charset=UTF-8")
    public List<Topic> getTopic(@RequestParam String courseName){;
        List<Topic> returnTopic = topicMapper.selectByCourseName(courseName);
        return returnTopic;
    }

    @GetMapping(value= "/course/topic",produces="application/json;charset=UTF-8")
    public List<Content> getContent(@RequestParam String topicName){;
        List<Content> returnContents = contentMapper.selectByTopicName(topicName);
        return returnContents;
    }

    @PostMapping(value= "/course/topic",produces="application/json;charset=UTF-8")
        public void createContent(@RequestParam String content, int type, int topicId, String topicName, String userName, int userId){
            if (type<0 || type >2) {
                System.out.println("content's type can only be within 0 to 2");
                return;
            }
            if (topicMapper.selectById(topicId) == null) {
                System.out.println("topic not exist");
                return;
            }
            Content post = new Content();
            post.setContent(content);
            post.setType(type);
            post.setTopicId(topicId);
            post.setTopicName(topicName);
            post.setUserName(userName);
            post.setUserId(userId);
            contentMapper.createContent(post);
    }

}
