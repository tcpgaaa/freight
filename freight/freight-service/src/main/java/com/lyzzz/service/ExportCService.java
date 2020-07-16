package com.lyzzz.service;

import com.lyzzz.pojo.ExportC;
import com.lyzzz.pojo.PageBean;

/**
@location：  freight\com.lyzzz.service  
@creatTime:   2020/7/10  9:15
@author:  Administrator
@remark:

*/
public interface ExportCService{


    int deleteByPrimaryKey(String exportId);

    int insert(ExportC record);

    int insertSelective(ExportC record);

    ExportC selectByPrimaryKey(String exportId);

    int updateByPrimaryKeySelective(ExportC record);

    int updateByPrimaryKey(ExportC record);

        PageBean listExportOfPage(PageBean pageBean, Integer state);

    void createExportC(ExportC exportC);

    void submitExportByExportId(String exportId);

    void cancelExportByExportId(String exportId);

}
