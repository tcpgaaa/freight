package com.lyzzz.service.impl;

import com.lyzzz.mapper.FactoryCMapper;
import com.lyzzz.pojo.FactoryC;
import com.lyzzz.service.FactoryCService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
@location：  freight\com.lyzzz.service.impl  
@creatTime:   2020/7/9  20:00
@author:  Administrator
@remark:

*/
@Service
public class FactoryCServiceImpl implements FactoryCService{

    @Resource
    private FactoryCMapper factoryCMapper;

    @Override
    public int deleteByPrimaryKey(String factoryId) {
        return factoryCMapper.deleteByPrimaryKey(factoryId);
    }

    @Override
    public int insert(FactoryC record) {
        return factoryCMapper.insert(record);
    }

    @Override
    public int insertSelective(FactoryC record) {
        return factoryCMapper.insertSelective(record);
    }

    @Override
    public FactoryC selectByPrimaryKey(String factoryId) {
        return factoryCMapper.selectByPrimaryKey(factoryId);
    }

    @Override
    public int updateByPrimaryKeySelective(FactoryC record) {
        return factoryCMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(FactoryC record) {
        return factoryCMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<FactoryC> listFactory() {
        return factoryCMapper.selectAll();
    }

}
