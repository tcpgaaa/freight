package com.lyzzz.service;

import com.lyzzz.pojo.ECharsData;

import java.util.List;

/**
 * @location： freight\com.lyzzz.service
 * @creatTime: 2020/7/13  9:45
 * @author: Administrator
 * @remark:
 */
public interface StatChartService {

    List<ECharsData> listFactorySaleData();

    List<ECharsData> listProductSaleData();

    List<ECharsData> listAccessLogData();
}
