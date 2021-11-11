package com.ELEC5620.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

// 映射SQL中表的entity类
@Data // equal @Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attendance {

    int id;
    int userId;
    String userName;
    int  courseId;
    String courseName;
    Date time;
}
