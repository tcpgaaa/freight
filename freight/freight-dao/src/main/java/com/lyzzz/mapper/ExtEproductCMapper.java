package com.lyzzz.mapper;

import com.lyzzz.pojo.ExtEproductC;

/**
@location：  freight\com.lyzzz.mapper  
@creatTime:   2020/7/10  9:23
@author:  Administrator
@remark:

*/
public interface ExtEproductCMapper {
    int deleteByPrimaryKey(String extEproductId);

    int insert(ExtEproductC record);

    int insertSelective(ExtEproductC record);

    ExtEproductC selectByPrimaryKey(String extEproductId);

    int updateByPrimaryKeySelective(ExtEproductC record);

    int updateByPrimaryKey(ExtEproductC record);
}