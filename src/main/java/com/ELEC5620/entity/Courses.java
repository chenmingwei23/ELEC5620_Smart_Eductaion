package com.ELEC5620.entity;

import lombok.*;

import java.util.List;

// 映射SQL中表的entity类
@Data // equal @Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Courses {
    private int id;
    private String name;
    List<Integer> studentLists;
    List<Topic> topicLists;
}

