package com.lyzzz.service;

import com.lyzzz.pojo.RoleModuleP;
    /**
@location：  freight\com.lyzzz.service  
@creatTime:   2020/7/9  13:23
@author:  Administrator
@remark:

*/
public interface RoleModulePService{


    int deleteByPrimaryKey(String moduleId,String roleId);

    int insert(RoleModuleP record);

    int insertSelective(RoleModuleP record);

        void createRoleModule(String roleId, String[] moduleIds);
    }
