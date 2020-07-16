package com.lyzzz.mapper;

import com.lyzzz.pojo.PackingListC;
import com.lyzzz.pojo.PackingListCVo;

import java.util.List;

/**
@location：  freight\com.lyzzz.mapper  
@creatTime:   2020/7/13  11:27
@author:  Administrator
@remark:

*/
public interface PackingListCMapper {
    int deleteByPrimaryKey(String packingListId);

    int insert(PackingListC record);

    int insertSelective(PackingListC record);

    PackingListC selectByPrimaryKey(String packingListId);

    int updateByPrimaryKeySelective(PackingListC record);

    int updateByPrimaryKey(PackingListC record);

    List<PackingListCVo> listPackingListVoOfPageByState();

    List<PackingListC> selectAll();

}