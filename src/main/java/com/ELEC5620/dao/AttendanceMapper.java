package com.ELEC5620.dao;
import com.ELEC5620.entity.Attendance;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AttendanceMapper {

    Attendance insertAttendance(Attendance attendance);

    List<Attendance> selectByUserId(int userId);

    List<Attendance> selectByCourseName(String courseName);

}
