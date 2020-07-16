package com.lyzzz.mapper;

import com.lyzzz.pojo.ShippingOrderC;

/**
@location：  freight\com.lyzzz.mapper  
@creatTime:   2020/7/13  14:34
@author:  Administrator
@remark:

*/
public interface ShippingOrderCMapper {
    int deleteByPrimaryKey(String shippingOrderId);

    int insert(ShippingOrderC record);

    int insertSelective(ShippingOrderC record);

    ShippingOrderC selectByPrimaryKey(String shippingOrderId);

    int updateByPrimaryKeySelective(ShippingOrderC record);

    int updateByPrimaryKey(ShippingOrderC record);
}