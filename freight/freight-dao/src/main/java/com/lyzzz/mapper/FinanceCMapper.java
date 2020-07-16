package com.lyzzz.mapper;

import com.lyzzz.pojo.FinanceC;

/**
@location：  freight\com.lyzzz.mapper  
@creatTime:   2020/7/13  14:05
@author:  Administrator
@remark:

*/
public interface FinanceCMapper {
    int deleteByPrimaryKey(String financeId);

    int insert(FinanceC record);

    int insertSelective(FinanceC record);

    FinanceC selectByPrimaryKey(String financeId);

    int updateByPrimaryKeySelective(FinanceC record);

    int updateByPrimaryKey(FinanceC record);
}