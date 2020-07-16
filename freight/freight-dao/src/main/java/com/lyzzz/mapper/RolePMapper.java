package com.lyzzz.mapper;

import com.lyzzz.pojo.RoleP;
import com.lyzzz.pojo.RoleUserP;

import java.util.List;

/**
 * @location： freight\com.lyzzz.mapper
 * @creatTime: 2020/7/8  8:51
 * @author: Administrator
 * @remark:
 */
public interface RolePMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(RoleP record);

    int insertSelective(RoleP record);

    RoleP selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(RoleP record);

    int updateByPrimaryKey(RoleP record);

    List<RoleP> selectAll();

    List<RoleP> selectByOrder();

    Integer getMaxOrderNo();

    List<RoleUserP> selectById(String roleId);
}