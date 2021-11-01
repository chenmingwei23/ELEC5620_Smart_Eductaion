package com.ELEC5620.entity;

import com.alibaba.fastjson.JSON;
import lombok.*;

import java.util.Date;
import java.util.List;

// 映射SQL中表的entity类
@Data // equal @Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private int id;
    private int pmId;
    private int companyId;
    private int type;           // 0- Project Manager; 1- Project Member; 2- Client;
    private int status;         // 0- Active; 1-  Deleted; We dont actually delete data, the deleted data can be used in data science .
    private String companyName;
    private String password;
    private String firstName;
    private String lastName;
    private String department;
    private String position;
    private String email;
    private String otherContactName;
    private String otherContactNumber;
    private String landline;
    private String mobile;
    private String address;
    private Date createTime;    // this data can be used in data science
}
