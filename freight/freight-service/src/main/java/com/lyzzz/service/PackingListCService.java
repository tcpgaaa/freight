package com.lyzzz.service;

import com.lyzzz.pojo.PackingListC;
import com.lyzzz.pojo.PageBean;

/**
 * @location： freight\com.lyzzz.service
 * @creatTime: 2020/7/13  11:27
 * @author: Administrator
 * @remark:
 */
public interface PackingListCService {


    int deleteByPrimaryKey(String packingListId);

    int insert(PackingListC record);

    int insertSelective(PackingListC record);

    PackingListC selectByPrimaryKey(String packingListId);

    int updateByPrimaryKeySelective(PackingListC record);

    int updateByPrimaryKey(PackingListC record);

    PageBean listPackingListOfPageByState(PageBean pageBean);

    PageBean listPackingListOfPage(PageBean pageBean);

    int createPackingList(String[] exportId, PackingListC packingListC);

    int submitPackingList(String packingListId);

    void cancelPackingList(String packingListId);
}
