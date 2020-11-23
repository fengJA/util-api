package com.fj.redis.entity;

import java.util.List;

/**
 * @author Liuruixia
 * @Description:
 * @date 2019/07/22
 */
public class PagingResultVO<T> {
    public List<T> data;
    public int count;
    public int pageIndex;
    public int pageSize;

    public PagingResultVO(){

    }

    public PagingResultVO(int pageIndex, int pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public PagingResultVO(int pageIndex, int pageSize, List<T> data, int count) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.data = data;
        this.count = count;
    }
}
