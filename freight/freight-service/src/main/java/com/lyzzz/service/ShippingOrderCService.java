package com.lyzzz.service;

import com.lyzzz.pojo.ShippingOrderC;

/**
 * @location： freight\com.lyzzz.service
 * @creatTime: 2020/7/13  14:33
 * @author: Administrator
 * @remark:
 */
public interface ShippingOrderCService {


    int deleteByPrimaryKey(String shippingOrderId);

    int insert(ShippingOrderC record);

    int insertSelective(ShippingOrderC record);

    ShippingOrderC selectByPrimaryKey(String shippingOrderId);

    int updateByPrimaryKeySelective(ShippingOrderC record);

    int updateByPrimaryKey(ShippingOrderC record);

    void submitShippingOrder(String packingListId);

    void createShippingOrder(ShippingOrderC shippingOrderC);

    void cancelShippingOrder(String packingListId);

}

