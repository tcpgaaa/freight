package com.lyzzz.mapper;

import com.lyzzz.pojo.ExportProductC;

/**
@location：  freight\com.lyzzz.mapper  
@creatTime:   2020/7/10  9:23
@author:  Administrator
@remark:

*/
public interface ExportProductCMapper {
    int deleteByPrimaryKey(String exportProductId);

    int insert(ExportProductC record);

    int insertSelective(ExportProductC record);

    ExportProductC selectByPrimaryKey(String exportProductId);

    int updateByPrimaryKeySelective(ExportProductC record);

    int updateByPrimaryKey(ExportProductC record);
}