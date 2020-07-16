package com.lyzzz.service;

import com.lyzzz.pojo.FinanceC;
    /**
@location：  freight\com.lyzzz.service  
@creatTime:   2020/7/13  14:05
@author:  Administrator
@remark:

*/
public interface FinanceCService{


    int deleteByPrimaryKey(String financeId);

    int insert(FinanceC record);

    int insertSelective(FinanceC record);

    FinanceC selectByPrimaryKey(String financeId);

    int updateByPrimaryKeySelective(FinanceC record);

    int updateByPrimaryKey(FinanceC record);

        void createFinanceC(FinanceC financeC);

        void submitFinanceC(String packingListId);

        void cancelFinanceC(String packingListId);

    }
