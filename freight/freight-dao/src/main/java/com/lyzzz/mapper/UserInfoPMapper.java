package com.lyzzz.mapper;

import com.lyzzz.pojo.UserInfoP;

import java.util.List;

/**
@location：  freight\com.lyzzz.mapper  
@creatTime:   2020/7/6  20:07
@author:  Administrator
@remark:

*/
public interface UserInfoPMapper {
    int deleteByPrimaryKey(String userInfoId);

    int insert(UserInfoP record);

    int insertSelective(UserInfoP record);

    UserInfoP selectByPrimaryKey(String userInfoId);

    int updateByPrimaryKeySelective(UserInfoP record);

    int updateByPrimaryKey(UserInfoP record);

    List<UserInfoP> selectAll();

    List<UserInfoP> selectManager(String userId);
}