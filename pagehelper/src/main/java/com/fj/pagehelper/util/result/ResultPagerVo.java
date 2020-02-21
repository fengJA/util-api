package com.fj.pagehelper.util.result;

/**
 *  分页返回信息操作
 */
public class ResultPagerVo extends ResultVo {

    private Integer pageNo;//当前页数
    private Integer total;//总页数

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ResultPagerVo{" +
                "pageNo=" + pageNo +
                ", total=" + total +
                '}';
    }
}
