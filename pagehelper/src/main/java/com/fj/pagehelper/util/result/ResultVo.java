package com.fj.pagehelper.util.result;

/**
 *  返回信息操作
 */
public class ResultVo {

    private String code;//业务代码，参见Const
    private String msg;//业务信息
    private Object body;//业务体

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

    @Override
    public String toString() {
        return "ResultVo{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", body=" + body +
                '}';
    }
}
