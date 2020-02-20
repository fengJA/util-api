package com.fj.test.gmall.vo;

import com.fj.test.gmall.entity.Pms;
import com.fj.test.gmall.entity.Ums;
import lombok.Data;

import java.util.List;

@Data
public class Param extends Pms {
    /*private String des;
    private String name;
*/
    private List<Ums> ums;
}
