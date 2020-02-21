package com.fj.pagehelper.util.page;

/**
 * 后端分页控件
 */
public class Pager {
    private String code;//业务代码，参见Const
    private String msg;//业务信息
    private Object body;//业务体
    private Integer pageNo;//当前页数
    private Integer total;//总页数

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        if (pageNo == null) {
            pageNo = 0;
        }
        if(pageNo==1) {
            pageNo = 0;
        }else {
            this.pageNo = pageNo*5+1;
        }
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
