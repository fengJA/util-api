package com.fj.test.gmall.service;

import com.fj.test.gmall.entity.Pms;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fj.test.gmall.vo.Param;
import com.fj.test.gmall.vo.PmsQueryParam;
import com.fj.test.gmall.vo.PmsQueyPageInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fj
 * @since 2020-02-19
 */
public interface PmsService extends IService<Pms> {

    void savePms(Param param);

    PmsQueyPageInfo selectPageInfo(Long pageNum, Long pageSize);

    PmsQueyPageInfo keyWorldQuery(PmsQueryParam pmsQueryParam, Long pageNum, Long pageSize);
}
