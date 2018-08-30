
package com.liuyang19900520.robotlife.user.common.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class PageBean<T> implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The records total.
     */
    private String recordsTotal;

    private Integer pagesTotal;

    private Integer currentPage;

    /**
     * The data.
     */
    private List<T> data;


}
