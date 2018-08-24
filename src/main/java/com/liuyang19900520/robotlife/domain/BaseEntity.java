package com.liuyang19900520.robotlife.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: robotlife-blog-java
 * @description:
 * @author: LiuYang
 * @create: 2018-07-13 13:55
 **/
@Data
public class BaseEntity implements Serializable {

    Long createBy;
    @JsonFormat(timezone = "GMT+9", pattern = "yyyy-MM-dd HH:mm")
    Date createAt;
    Long updateBy;
    @JsonFormat(timezone = "GMT+9", pattern = "yyyy-MM-dd HH:mm")
    Date updateAt;
    String deleteFlag;


}
