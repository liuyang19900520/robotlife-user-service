package com.liuyang19900520.robotlife.user.common.pojo;

/**
 * Message类别
 *
 * @author liuya
 */

public enum Messages {

    //HttpStatus业务相关，负责exception的管理
    UNAUTHORIZATION(401, "认证失败"),
    UNFORBIDDEN(403, "授权失败"),

    SIGN_IN_SUCCESS(900000, "登录成功"),
    SIGN_IN_FAILED(900001, "登录失败"),
    SIGN_IN_AUTH_FAILED(900002, "错误的凭证，用户名或密码不正确"),
    SIGN_IN_STATUS_FAILED(900003, "账户已锁定"),
    SIGN_IN_TIMES_FAILED(900004, "错误次数过多"),


    SIGN_UP_SUCCESS_MOBILE(10003, "注册用户成功"),
    SIGN_UP_SUCCESS_EMAIL(10004, "注册用户成功，请激活"),
    SIGN_UP_FAILED_MESSAGE_ERROR(10002, "验证短信发送失败"),
    SIGN_UP_FAILED_USER_EXISTED(900013, "注册用户存在"),

    SIGN_UP_ACTIVE_SUCCESS(900011, "激活成功"),
    SIGN_UP_ACTIVE_FAILED(900012, "激活失败"),
    USER_SIGN_IN_SUCCESS(10009, "注册登录成功"),
    USER_SIGN_IN_FAILED(10008, "注册登录失败"),

    JWT_TOKEN_AUTH_FAILED(900021, "JWT TOKEN 认证失败");


    private final int value;

    private final String msg;

    Messages(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public int value() {
        return value;
    }

    public String msg() {
        return msg;
    }
}
