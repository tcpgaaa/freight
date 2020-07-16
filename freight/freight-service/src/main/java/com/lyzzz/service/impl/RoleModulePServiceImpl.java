package com.lyzzz.service.impl;

import com.lyzzz.mapper.RoleModulePMapper;
import com.lyzzz.pojo.RoleModuleP;
import com.lyzzz.service.RoleModulePService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
@location：  freight\com.lyzzz.service.impl  
@creatTime:   2020/7/9  13:23
@author:  Administrator
@remark:

*/
@Service
public class RoleModulePServiceImpl implements RoleModulePService{

    @Resource
    private RoleModulePMapper roleModulePMapper;

    @Override
    public int deleteByPrimaryKey(String moduleId,String roleId) {
        return roleModulePMapper.deleteByPrimaryKey(moduleId,roleId);
    }

    @Override
    public int insert(RoleModuleP record) {
        return roleModulePMapper.insert(record);
    }

    @Override
    public int insertSelective(RoleModuleP record) {
        return roleModulePMapper.insertSelective(record);
    }

    @Override
    public void createRoleModule(String roleId, String[] moduleIds) {

        //先删除原来的权限
        roleModulePMapper.deleteById(roleId);

        //再保存新的权限
        for (int i = 0; i < moduleIds.length; i++) {
            String moduleId = moduleIds[i];
            RoleModuleP roleModuleP = new RoleModuleP();
            roleModuleP.setRoleId(roleId);
            roleModuleP.setModuleId(moduleId);
            roleModulePMapper.insertSelective(roleModuleP);
        }

    }

}
