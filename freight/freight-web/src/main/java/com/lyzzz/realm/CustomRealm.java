package com.lyzzz.realm;

import com.lyzzz.pojo.CurrentUser;
import com.lyzzz.pojo.ModuleP;
import com.lyzzz.pojo.UserP;
import com.lyzzz.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        // 授权
        // 获取用户
        CurrentUser currentUser = (CurrentUser) principal.getPrimaryPrincipal();
        UserP user = currentUser.getUserP();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 填充用户权限
        List<ModuleP> modulePList = userService.getPermissionsByUserId(user.getUserId());
        if (modulePList != null && modulePList.size() > 0) {
            for (ModuleP module : modulePList) {
                authorizationInfo.addStringPermission(module.getCpermission());
            }
        }

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //  认证
        String username = (String) authenticationToken.getPrincipal();
        //查询用户名
        UserP user = userService.login(username);
        if(user==null){
            return null;
        }
        CurrentUser currentUser = userService.findUserInfoDeptByUser(user);
        currentUser.setUserP(user);

        //盐
        String salt = user.getUserName() + user.getUserId();
        System.out.println(salt+"-----");
        //返回认证信息
        return new SimpleAuthenticationInfo(currentUser, user.getPassword(),
                ByteSource.Util.bytes(salt), getName());

    }
    // 清除缓存
    public void clearCached(){
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}
