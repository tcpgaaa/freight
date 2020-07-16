package com.lyzzz.service;

import com.lyzzz.pojo.*;

import java.util.List;

public interface UserService {

    UserP login(String username);

    CurrentUser findUserInfoDeptByUser(UserP userP);

    List<ModuleP> getPermissionsByUserId(String userId);

    PageBean listUserOfPage(PageBean pageBean);

    UserP selectUserById(String id);

    List<UserInfoP> selectUserInfo();

    void addUser(UserP userP, UserInfoP userInfoP);

    UserInfoP selectUserInfoById(String id);

    int updateUserAndUserInfo(UserInfoP userInfoP, UserP userP);

    DeleteResult deleteUser(String[] userIds);
}
