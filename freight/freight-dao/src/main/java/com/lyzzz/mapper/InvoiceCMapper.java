package com.lyzzz.mapper;

import com.lyzzz.pojo.InvoiceC;

/**
@location：  freight\com.lyzzz.mapper  
@creatTime:   2020/7/13  11:35
@author:  Administrator
@remark:

*/
public interface InvoiceCMapper {
    int deleteByPrimaryKey(String invoiceId);

    int insert(InvoiceC record);

    int insertSelective(InvoiceC record);

    InvoiceC selectByPrimaryKey(String invoiceId);

    int updateByPrimaryKeySelective(InvoiceC record);

    int updateByPrimaryKey(InvoiceC record);
}