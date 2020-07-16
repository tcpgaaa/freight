package com.lyzzz.mapper;

import com.lyzzz.pojo.ExtCproductC;

import java.util.List;

/**
@location：  freight\com.lyzzz.mapper  
@creatTime:   2020/7/9  14:19
@author:  Administrator
@remark:

*/
public interface ExtCproductCMapper {
    int deleteByPrimaryKey(String extCproductId);

    int insert(ExtCproductC record);

    int insertSelective(ExtCproductC record);

    ExtCproductC selectByPrimaryKey(String extCproductId);

    int updateByPrimaryKeySelective(ExtCproductC record);

    int updateByPrimaryKey(ExtCproductC record);

    Double getTotalAmountByContractId(String contractId);

    int delete(String id);

    List<ExtCproductC> getPageList(String cid);
}