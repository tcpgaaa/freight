package com.lyzzz.service;

import com.lyzzz.pojo.ExtCproductC;
import com.lyzzz.pojo.PageBean;

/**
@location：  freight\com.lyzzz.service  
@creatTime:   2020/7/9  14:19
@author:  Administrator
@remark:

*/
public interface ExtCproductCService{


    int deleteByPrimaryKey(String extCproductId);

    int insert(ExtCproductC record);

    int insertSelective(ExtCproductC record);

    ExtCproductC selectByPrimaryKey(String extCproductId);

    int updateByPrimaryKeySelective(ExtCproductC record);

    int updateByPrimaryKey(ExtCproductC record);

    void createExtCproductC(ExtCproductC extCproductC, String cid);

    PageBean selectAll(PageBean pageBean, String cid);
}
