package com.lyzzz.service.impl;

import com.lyzzz.mapper.RoleUserPMapper;
import com.lyzzz.pojo.RoleUserP;
import com.lyzzz.service.RoleUserPService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
@location：  freight\com.lyzzz.service.impl  
@creatTime:   2020/7/8  8:50
@author:  Administrator
@remark:

*/
@Service
public class RoleUserPServiceImpl implements RoleUserPService{

    @Resource
    private RoleUserPMapper roleUserPMapper;

    @Override
    public int deleteByPrimaryKey(String roleId,String userId) {
        return roleUserPMapper.deleteByPrimaryKey(roleId,userId);
    }

    @Override
    public int insert(RoleUserP record) {
        return roleUserPMapper.insert(record);
    }

    @Override
    public int insertSelective(RoleUserP record) {
        return roleUserPMapper.insertSelective(record);
    }

    @Override
    public List<RoleUserP> listRoleUser(String id) {
        return roleUserPMapper.listRoleUser(id);
    }

    @Override
    public int addRoleUser(String id, String[] roleIds) {
        // 删除原来的角色
        int delete = roleUserPMapper.deleteByUserId(id);

        // 保存新角色
        for (int i =0;i<roleIds.length;i++){
            String roleId = roleIds[i];

            roleUserPMapper.insertSelective(new RoleUserP(roleId,id));

        }
        return delete;
    }

}
