package com.ELEC5620.entity;

import lombok.*;

import java.util.Date;

// 映射SQL中表的entity类
@Data // equal @Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Demo {

    private int id;
    private int type;
    private int status;
    private String username;
    private String password;
    private String company;
    private String salt;
    private String email;
    private String activationCode;
    private String headerUrl;
    private Date createTime;
}
