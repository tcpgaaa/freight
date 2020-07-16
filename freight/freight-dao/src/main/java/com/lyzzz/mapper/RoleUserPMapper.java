package com.lyzzz.mapper;

import com.lyzzz.pojo.RoleUserP;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @location： freight\com.lyzzz.mapper
 * @creatTime: 2020/7/8  8:50
 * @author: Administrator
 * @remark:
 */
public interface RoleUserPMapper {
    int deleteByPrimaryKey(@Param("roleId") String roleId, @Param("userId") String userId);

    int insert(RoleUserP record);

    int insertSelective(RoleUserP record);

    List<RoleUserP> listRoleUser(String id);

    int deleteByUserId(String id);

    List<RoleUserP> selectById(String roleId);
}