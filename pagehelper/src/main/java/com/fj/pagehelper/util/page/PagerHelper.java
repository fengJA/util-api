package com.fj.pagehelper.util.page;

import java.util.Map;

/**
 * pageUtil
 * @author PC043
 */
public class PagerHelper {
    public static Map getPage(Map<String, Object> viewContent){
        Object obj = viewContent.get("pageNo");
        if(obj!=null) {
            Integer pageno = (Integer) obj;
            if (pageno > 0) {
                Integer i = (pageno-1)*20;
                viewContent.put("pageNo",i);
            }
        }
        return viewContent;
    }
}
