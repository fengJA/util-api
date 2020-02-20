package com.fj.test.gmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fj.test.gmall.entity.Pms;
import com.fj.test.gmall.entity.Ums;
import com.fj.test.gmall.mapper.PmsMapper;
import com.fj.test.gmall.mapper.UmsMapper;
import com.fj.test.gmall.service.PmsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fj.test.gmall.vo.Param;
import com.fj.test.gmall.vo.PmsQueryParam;
import com.fj.test.gmall.vo.PmsQueyPageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fj
 * @since 2020-02-19
 */
@Service
@Slf4j
public class PmsServiceImpl extends ServiceImpl<PmsMapper, Pms> implements PmsService {

    @Autowired
    PmsMapper pmsMapper;

    @Autowired
    UmsMapper umsMapper;

    @Override
    public void savePms(Param param) {
        Pms pms = new Pms();
        BeanUtils.copyProperties(param,pms);
        pmsMapper.insert(pms);
        log.debug("pms的ID{}",pms.getId());

        List<Ums> ums = param.getUms();
        ums.forEach((ums1)->{
            ums1.setPmsId(param.getId());
            umsMapper.insert(ums1);
        });
    }

    @Override
    public PmsQueyPageInfo selectPageInfo(Long pageNum, Long pageSize) {

        IPage<Pms> pmsIPage = pmsMapper.selectPage(new Page<Pms>(pageNum, pageSize), null);
        long current = pmsIPage.getCurrent();
        long total = pmsIPage.getTotal();

        return new PmsQueyPageInfo(pmsIPage.getTotal(),pmsIPage.getPages(),pageSize,pmsIPage.getRecords(),pageNum);
    }

    @Override
    public PmsQueyPageInfo keyWorldQuery(PmsQueryParam pmsQueryParam, Long pageNum, Long pageSize) {
        QueryWrapper<Pms> pmsQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(pmsQueryParam.getName())){
            pmsQueryWrapper.eq("name",pmsQueryParam.getName());
        }
        if (!StringUtils.isEmpty(pmsQueryParam.getDes())){
            pmsQueryWrapper.like("des",pmsQueryParam.getDes());
        }

        IPage<Pms> pmsIPage = pmsMapper.selectPage(new Page<>(pageNum, pageSize), pmsQueryWrapper);
        return PmsQueyPageInfo.getVo(pmsIPage,pageSize);
    }
}
