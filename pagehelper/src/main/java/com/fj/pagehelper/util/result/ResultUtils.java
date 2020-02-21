package com.fj.pagehelper.util.result;

import com.fj.pagehelper.util.consts.Const;
import com.github.pagehelper.Page;

/**
 *  返回客户端信息工具类(分页、不分页)
 */
public class ResultUtils {

    /**
     * 返回成功信息(带消息参数)
     */
    public static ResultVo success(Object obj, String msg){
        ResultVo resultVo = new ResultVo();
        resultVo.setBody(obj);
        resultVo.setMsg(msg);
        resultVo.setCode(Const.STATUS_SUCESS);
        return resultVo;
    }

    /**
     * 返回成功信息(带消息参数)
     */
    public static ResultVo success(Object obj){
        return success(obj,null);
    }

    /**
     * 返回成功信息(不带消息参数)
     */
    public static ResultVo success(){
        return success(null,null);
    }


    /**
     * 返回错误消息(带参数)
     */
    public static ResultVo error(String code,String msg){
        ResultVo resultVo = new ResultVo();
        resultVo.setMsg(msg);
        resultVo.setCode(code);
        return resultVo;
    }

    /**
     * 返回分页成功信息(带消息参数)
     * @param obj
     * @param msg
     * @return
     */
    public static ResultPagerVo pagerSuccess(Object obj,String msg,Page page) {
        ResultPagerVo pagerVo = new ResultPagerVo();
        pagerVo.setBody(obj);
        pagerVo.setMsg(msg);
        pagerVo.setTotal((int)page.getTotal());
        pagerVo.setPageNo(page.getPageNum());
        pagerVo.setCode(Const.STATUS_SUCESS);
        return pagerVo;
    }

    /**
     * 返回分页成功信息(带消息参数)
     * @return
     */
    public static ResultPagerVo pagerSuccess(Object obj,Page page) {
        return pagerSuccess(obj,null,page);
    }

    /**
     * 返回错误消息(带参数)
     */
    public static ResultPagerVo pagerError(String code, String msg){
        ResultPagerVo pagerVo = new ResultPagerVo();
        pagerVo.setMsg(msg);
        pagerVo.setCode(code);
        return pagerVo;
    }

}
