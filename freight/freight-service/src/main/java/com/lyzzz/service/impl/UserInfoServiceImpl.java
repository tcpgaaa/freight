package com.lyzzz.service.impl;

import com.lyzzz.mapper.UserInfoPMapper;
import com.lyzzz.pojo.UserInfoP;
import com.lyzzz.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @location： freight\com.lyzzz.service.impl
 * @creatTime: 2020/7/8  8:45
 * @author: Administrator
 * @remark:
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoPMapper userInfoPMapper;
    @Override
    public UserInfoP getUserInfoById(String id) {
        return userInfoPMapper.selectByPrimaryKey(id);
    }
}
