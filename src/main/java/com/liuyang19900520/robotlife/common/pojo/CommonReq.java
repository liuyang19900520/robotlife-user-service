package com.liuyang19900520.robotlife.common.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liuya
 */
@NoArgsConstructor
@Data
public class CommonReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private String enterpriseCode;
    private String storeId;
    private String terminalId;
    private String targetCustomerId;
    private String accessCode;
    private String terminalType;

    private Integer pageNo;
    private Integer rows;

}
