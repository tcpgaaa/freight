package com.lyzzz.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lyzzz.pojo.ExtEproductC;
import com.lyzzz.mapper.ExtEproductCMapper;
import com.lyzzz.service.ExtEproductCService;
/**
@location：  freight\com.lyzzz.service.impl  
@creatTime:   2020/7/10  9:23
@author:  Administrator
@remark:

*/
@Service
public class ExtEproductCServiceImpl implements ExtEproductCService{

    @Resource
    private ExtEproductCMapper extEproductCMapper;

    @Override
    public int deleteByPrimaryKey(String extEproductId) {
        return extEproductCMapper.deleteByPrimaryKey(extEproductId);
    }

    @Override
    public int insert(ExtEproductC record) {
        return extEproductCMapper.insert(record);
    }

    @Override
    public int insertSelective(ExtEproductC record) {
        return extEproductCMapper.insertSelective(record);
    }

    @Override
    public ExtEproductC selectByPrimaryKey(String extEproductId) {
        return extEproductCMapper.selectByPrimaryKey(extEproductId);
    }

    @Override
    public int updateByPrimaryKeySelective(ExtEproductC record) {
        return extEproductCMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ExtEproductC record) {
        return extEproductCMapper.updateByPrimaryKey(record);
    }

}
