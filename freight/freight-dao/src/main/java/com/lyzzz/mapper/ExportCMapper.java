package com.lyzzz.mapper;

import com.lyzzz.pojo.ExportC;
import com.lyzzz.pojo.ExportCVo;

import java.util.List;

/**
@location：  freight\com.lyzzz.mapper  
@creatTime:   2020/7/10  9:17
@author:  Administrator
@remark:

*/
public interface ExportCMapper {
    int deleteByPrimaryKey(String exportId);

    int insert(ExportC record);

    int insertSelective(ExportC record);

    ExportC selectByPrimaryKey(String exportId);

    int updateByPrimaryKeySelective(ExportC record);

    int updateByPrimaryKey(ExportC record);

    List<ExportCVo> listExportOfPage(Integer state);

}