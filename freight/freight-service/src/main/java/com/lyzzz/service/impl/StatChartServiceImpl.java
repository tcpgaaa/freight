package com.lyzzz.service.impl;

import com.lyzzz.mapper.ContractProductCMapper;
import com.lyzzz.pojo.ECharsData;
import com.lyzzz.service.StatChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @location： freight\com.lyzzz.service.impl
 * @creatTime: 2020/7/13  9:46
 * @author: Administrator
 * @remark:
 */
@Service("statChartService")
public class StatChartServiceImpl implements StatChartService {

    @Autowired
    private ContractProductCMapper contractProductCMapper;

    @Override
    public List<ECharsData> listFactorySaleData() {
        return contractProductCMapper.listFactorySaleData();
    }

    @Override
    public List<ECharsData> listProductSaleData() {
        return contractProductCMapper.listProductSaleData();

    }

    @Override
    public List<ECharsData> listAccessLogData()  {
        return contractProductCMapper.listAccessLogData();
    }
}
