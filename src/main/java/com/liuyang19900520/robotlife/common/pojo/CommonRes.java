package com.liuyang19900520.robotlife.common.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
public class CommonRes implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("isSuccess")
    private boolean isSuccess;

    private String errorCode;

    private String errorMessage;

    // レスポンス
    private Object data;

    public CommonRes(boolean isSuccess, String errorCode, String errorMessage, Object data) {
        this.isSuccess = isSuccess;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    /**
     * @param isSuccess
     * @param errorCode
     * @param errorMessage
     * @param data
     * @return
     * @Title: build
     * @Description: build the complete result
     * @return: CommonResult
     */
    public static CommonRes build(boolean isSuccess, String errorCode, String errorMessage, Object data) {
        return new CommonRes(isSuccess, errorCode, errorMessage, data);
    }

    public static CommonRes buildError(String errorCode, String errorMessage) {
        return new CommonRes(false, errorCode, errorMessage, "");
    }

    public static CommonRes buildOk(Object data) {
        if (data == null) {
            data = "";
        }
        return new CommonRes(true, "", "", data);
    }

    @JsonIgnore
    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
