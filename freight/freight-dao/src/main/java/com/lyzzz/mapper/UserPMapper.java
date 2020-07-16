package com.lyzzz.mapper;

import com.lyzzz.pojo.UserP;

import java.util.List;

/**
@location：  freight\com.mapper  
@creatTime:   2020/7/6  17:52
@author:  Administrator
@remark:

*/
public interface UserPMapper {
    UserP login(String name);

    int deleteByPrimaryKey(String userId);

    int insert(UserP record);

    int insertSelective(UserP record);

    UserP selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(UserP record);

    int updateByPrimaryKey(UserP record);

    List<UserP> selectDeptHasUser(String deptId);

    List<UserP> selectAll();


}