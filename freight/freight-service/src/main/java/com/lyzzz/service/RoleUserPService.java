package com.lyzzz.service;

import com.lyzzz.pojo.RoleUserP;

import java.util.List;

/**
 * @location： freight\com.lyzzz.service
 * @creatTime: 2020/7/8  8:50
 * @author: Administrator
 * @remark:
 */
public interface RoleUserPService {


    int deleteByPrimaryKey(String roleId, String userId);

    int insert(RoleUserP record);

    int insertSelective(RoleUserP record);

    List<RoleUserP> listRoleUser(String id);

    int addRoleUser(String id, String[] roleId);
}
