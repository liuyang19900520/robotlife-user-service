package com.liuyang19900520.robotlife.user.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: robotlife-blog-java
 * @description:
 * @author: LiuYang
 * @create: 2018-07-13 13:51
 **/
@Data
public class Category extends BaseEntity implements Serializable {

    Long id;
    Long categoryId;
    Long parentId;
    String categoryContent;
    Integer categoryRank;
}
