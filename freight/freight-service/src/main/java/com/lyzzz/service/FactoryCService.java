package com.lyzzz.service;

import com.lyzzz.pojo.FactoryC;

import java.util.List;

/**
@location：  freight\com.lyzzz.service  
@creatTime:   2020/7/9  20:00
@author:  Administrator
@remark:

*/
public interface FactoryCService{


    int deleteByPrimaryKey(String factoryId);

    int insert(FactoryC record);

    int insertSelective(FactoryC record);

    FactoryC selectByPrimaryKey(String factoryId);

    int updateByPrimaryKeySelective(FactoryC record);

    int updateByPrimaryKey(FactoryC record);

        List<FactoryC> listFactory();

    }
