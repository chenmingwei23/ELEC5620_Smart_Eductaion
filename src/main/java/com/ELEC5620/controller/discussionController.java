package com.ELEC5620.controller;

import com.ELEC5620.dao.CourseMapper;
import com.ELEC5620.entity.Courses;
import com.ELEC5620.entity.Discussion;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class discussionController {
    CourseMapper courseMapper;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value= "/discussion/course",produces="application/json;charset=UTF-8")
    public List<Courses> getCourse(@RequestParam int courseId){
        Courses course = courseMapper.selectById(courseId);

        System.out.println("hello!"+course.getId());
        ArrayList<Courses> returnTopic = new ArrayList<>();

        return returnTopic;
    }


}
