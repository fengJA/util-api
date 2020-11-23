package com.fj.redis.util.valid;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liubin
 * @DESC 参数校验
 * @createTime 2020-04-09
 */

public class ValidateParamUtil {

    /**
     * 判断对象是否为null和空
     *
     * @param object
     * @return
     */
    public static boolean isObjectNull(Object object) {
        if (object == null) {
            return true;
        } else if (object instanceof Collection) {
            return ((Collection) object).isEmpty() ? true : false;
        } else if (object instanceof Map) {
            return ((Map) object).isEmpty() ? true : false;
        } else if (object instanceof String) {
            return ((String) object).trim().length() == 0 ? true : false;
        }
        return false;
    }


    /**
     * 校验是否存在中文 存在 true 反之false
     * @param countname
     * @return
     */
    public static boolean isChinese(String countname){
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(countname);
        if (m.find()) {
            return true;
        }
        return false;
    }

    public static void main(String [] args){
        System.out.println(ValidateParamUtil.isChinese("LIU斌"));
    }

}
