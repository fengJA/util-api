package com.fj.pagehelper.vo;

import com.fj.pagehelper.util.page.PageParamBase;
import lombok.Data;

@Data
public class QueryParam extends PageParamBase {
    private String name;
    private String des;
}
