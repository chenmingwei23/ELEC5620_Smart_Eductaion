package com.ELEC5620.entity;

import lombok.*;

import java.util.Date;
import java.util.List;

// 映射SQL中表的entity类
@Data // equal @Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Topic {
    private int id;
    private String title;
    private String content;
    private int authorId;
    private String authorName;
}
