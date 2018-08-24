package com.liuyang19900520.robotlife.common.pojo;

/**
 * Message类别
 *
 * @author liuya
 */

public enum Messages {

    //HttpStatus业务相关，负责exception的管理

    MESSAGE_ERROR(10002, "验证短信发送失败"),
    USER_EXISTED(10001, "注册用户存在"),
    USER_SIGN_UP_SUCCESS_MOBILE(10003, "注册用户成功"),
    USER_SIGN_UP_SUCCESS_EMAIL(10003, "注册用户成功，请激活");

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
