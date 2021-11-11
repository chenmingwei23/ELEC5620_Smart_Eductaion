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

    @PostMapping(value= "/course",produces="application/json;charset=UTF-8")
    public List<Topic> getTopic(@RequestBody Map<String, Object> data){
        String courseName = data.get("courseName").toString();
        List<Topic> returnTopic = topicMapper.selectByCourseName(courseName);
        return returnTopic;
    }

    @GetMapping(value= "/course/topic",produces="application/json;charset=UTF-8")
    public List<Content> getContent(@RequestParam Map<String, Object> data){
        String topicName = data.get("topicName").toString();
        System.out.println("============"+topicName);
        List<Content> returnContents = contentMapper.selectByTopicName(topicName);
        return returnContents;
    }

    @PostMapping(value= "/course/add/topic",produces="application/json;charset=UTF-8")
    public void addTopic(@RequestBody Map<String, Object> data){
        String courseName = data.get("courseName").toString();
        String title = data.get("title").toString();
        int authorId = Integer.parseInt(data.get("authorId").toString());
        String authorName = data.get("authorName").toString();

        Topic topic = new Topic();
        topic.setCourseId(1);
        topic.setCourseName(courseName);
        topic.setTitle(title);
        topic.setAuthorId(authorId);
        topic.setAuthorName(authorName);
        topicMapper.insertTopic(topic);
    }

    @PostMapping(value= "/course/topic",produces="application/json;charset=UTF-8")
        public void createContent(@RequestBody Map<String, Object> data/*@RequestParam String content, int type, int topicId, String topicName, String userName, int userId*/){
        String content = data.get("content").toString();
        int type = Integer.parseInt(data.get("type").toString());
        int topicId = (int) System.currentTimeMillis();
        String topicName = data.get("topicName").toString();
        String userName = data.get("userName").toString();
        int userId = Integer.parseInt(data.get("userId").toString());
        if (type<0 || type >2) {
                System.out.println("content's type can only be within 0 to 2");
                return;
            }
//            if (type == 1 && topicMapper.selectById(topicId) == null) {
//                System.out.println("topic not exist");
//                return;
//            }
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
