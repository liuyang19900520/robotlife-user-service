package com.liuyang19900520.robotlife.domain.user;

import com.liuyang19900520.robotlife.domain.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liuya
 */
@Data
public class SysUser extends BaseEntity implements Serializable {
    private Long id;

    private Long userId;

    private String mobilePhone;

    private String userName;

    private String nickname;

    private String password;

    private String salt;

    private String mobileVersionCode;

    private String signature;

    private Boolean gender;

    private Long qq;

    private String email;

    private String avatar;

    private String province;

    private String city;

    private String regIp;

    private Integer score;

    private String status;

    private String token;

    private String refreshToken;


    public static String UN_ACTIVE_STATUS = "1";
    public static String ACTIVE_STATUS = "0";


}