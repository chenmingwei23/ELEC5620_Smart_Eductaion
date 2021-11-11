package com.ELEC5620.controller;

import com.ELEC5620.dao.AttendanceMapper;
import com.ELEC5620.dao.ContentMapper;
import com.ELEC5620.dao.CourseMapper;
import com.ELEC5620.dao.TopicMapper;
import com.ELEC5620.entity.*;
import com.ELEC5620.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/attendance")
@CrossOrigin(origins = "http://localhost:3000/")
public class AttendanceFrontendController {

    @Autowired
    AttendanceMapper attendanceMapper;
    @Autowired
    CourseMapper courseMapper;

    @GetMapping(value= "/getAttendanceByUserId",produces="application/json;charset=UTF-8")
    public List<Attendance> getAttendanceByUserId(int userId) {
        List<Attendance> res = attendanceMapper.selectByUserId(userId);
        return res;
    }

    @GetMapping(value= "/getAttendanceByCourseName",produces="application/json;charset=UTF-8")
    public List<Attendance> getAttendanceByCourseName(String courseName) {
        List<Attendance> res = attendanceMapper.selectByCourseName(courseName);
        return res;
    }

}
