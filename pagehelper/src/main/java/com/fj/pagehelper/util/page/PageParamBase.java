package com.fj.pagehelper.util.page;

import lombok.Data;

/**
 * 分页参数基本信息
 */
@Data
public class PageParamBase {
    /**
     * 当前页号
     */
    private Integer pageNumber;
    /**
     * 每页大小
     */
    private Integer pageSize;

}
