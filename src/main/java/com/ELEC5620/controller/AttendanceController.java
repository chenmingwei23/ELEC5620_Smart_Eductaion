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
@RequestMapping("/ai/api")
@CrossOrigin(origins = "http://localhost:3001/")
public class AttendanceController {

    @Autowired
    AttendanceMapper attendanceMapper;
    @Autowired
    CourseMapper courseMapper;

    @PostMapping(value= "/postAttendance",produces="application/json;charset=UTF-8")
    public List<String> postAttendance(String courseName, int userId, String userName) {
        List<String> res = new ArrayList<>();
        Attendance attendance = new Attendance();
        attendance.setUserID(userId);
        attendance.setCourseName(courseName);
        attendance.setUserName(userName);
        attendanceMapper.insertAttendance(attendance);
        System.out.println("posted the attendance data to database "+ attendance.getUserName());
        return res;
    }


}
