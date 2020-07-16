package com.lyzzz.mapper;

import com.lyzzz.pojo.RoleModuleP;
import org.apache.ibatis.annotations.Param;

/**
@location：  freight\com.lyzzz.mapper  
@creatTime:   2020/7/9  13:23
@author:  Administrator
@remark:

*/
public interface RoleModulePMapper {
    int deleteByPrimaryKey(@Param("moduleId") String moduleId, @Param("roleId") String roleId);

    int insert(RoleModuleP record);

    int insertSelective(RoleModuleP record);

    void deleteById(String roleId);
}