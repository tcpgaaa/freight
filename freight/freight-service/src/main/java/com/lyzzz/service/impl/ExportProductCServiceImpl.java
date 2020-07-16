package com.lyzzz.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lyzzz.pojo.ExportProductC;
import com.lyzzz.mapper.ExportProductCMapper;
import com.lyzzz.service.ExportProductCService;
/**
@location：  freight\com.lyzzz.service.impl  
@creatTime:   2020/7/10  9:23
@author:  Administrator
@remark:

*/
@Service
public class ExportProductCServiceImpl implements ExportProductCService{

    @Resource
    private ExportProductCMapper exportProductCMapper;

    @Override
    public int deleteByPrimaryKey(String exportProductId) {
        return exportProductCMapper.deleteByPrimaryKey(exportProductId);
    }

    @Override
    public int insert(ExportProductC record) {
        return exportProductCMapper.insert(record);
    }

    @Override
    public int insertSelective(ExportProductC record) {
        return exportProductCMapper.insertSelective(record);
    }

    @Override
    public ExportProductC selectByPrimaryKey(String exportProductId) {
        return exportProductCMapper.selectByPrimaryKey(exportProductId);
    }

    @Override
    public int updateByPrimaryKeySelective(ExportProductC record) {
        return exportProductCMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ExportProductC record) {
        return exportProductCMapper.updateByPrimaryKey(record);
    }

}
