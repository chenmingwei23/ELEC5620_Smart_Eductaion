package com.ELEC5620.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 映射SQL中表的entity类
@Data // equal @Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Content {

    int id;
    String content;
    int type; // 0: content, 1: reply, 2: comment
    int topicId;
    String topicName;
    String userName;
    int userId;

}
