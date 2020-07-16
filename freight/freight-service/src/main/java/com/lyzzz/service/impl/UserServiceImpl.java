package com.lyzzz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyzzz.mapper.DeptPMapper;
import com.lyzzz.mapper.ModulePMapper;
import com.lyzzz.mapper.UserInfoPMapper;
import com.lyzzz.mapper.UserPMapper;
import com.lyzzz.pojo.*;
import com.lyzzz.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.*;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private MailSender mailSender;
    @Autowired
    private UserPMapper userPMapper;
    @Autowired
    private DeptPMapper deptPMapper;
    @Autowired
    private UserInfoPMapper userInfoPMapper;
    @Autowired
    private ModulePMapper modulePMapper;


    @Override
    // 登录
    public UserP login(String username) {
        return userPMapper.login(username);
    }

    @Override
    // 获取部门信息
    public CurrentUser findUserInfoDeptByUser(UserP userP) {
        CurrentUser currentUser = new CurrentUser();
        currentUser.setUserP(userP);// 设置用户基本信息
        currentUser.setDeptP(deptPMapper.selectByPrimaryKey(userP.getDeptId()));// 设置用户所属部门信息
        currentUser.setUserInfoP(userInfoPMapper.selectByPrimaryKey(userP.getUserId()));// 设置用户详细信息
        return currentUser;

    }

    @Override
    // 获取权限
    public List<ModuleP> getPermissionsByUserId(String userId) {
        return modulePMapper.getPermissionsByUserId(userId);
    }

    @Override
    // 用户列表
    public PageBean listUserOfPage(PageBean pageBean) {
        PageHelper.startPage(pageBean.getCurPage(),pageBean.getPageSize());

        List<UserP> list = userPMapper.selectAll();
        PageInfo<UserP> pageInfo = new PageInfo<>(list);
        pageBean.setDatas(pageInfo.getList());
        pageBean.setTotalRows(pageInfo.getTotal());
        pageBean.setTotalPages(pageInfo.getPages());

        return pageBean;
    }

    @Override
    public UserP selectUserById(String id) {
        return userPMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserInfoP> selectUserInfo() {
        return userInfoPMapper.selectAll();
    }

    @Override
    public void addUser(UserP userP, UserInfoP userInfoP) {
        // 获取当前用户登录信息
        Subject subject = SecurityUtils.getSubject();
        CurrentUser currentUser = (CurrentUser) subject.getPrincipal();
        UserP user = currentUser.getUserP();
        DeptP dept = currentUser.getDeptP();

        // 补全用户信息
        userP.setUserId(UUID.randomUUID().toString());
        // 初始密码
        int i = new Random().nextInt(10000);
        String password = userP.getUserName()+i;
        String salt = userP.getUserName()+userP.getUserId();
        userP.setPassword(new Md5Hash(password, salt,1).toString());
        userP.setCreateBy(user.getUserId());
        userP.setCreateDept(dept.getDeptId());
        userP.setUpdateBy(user.getUserId());
        userP.setUpdateTime(new Date());
        userP.setCreateTime(new Date());

        // userInfo
        userInfoP.setUserInfoId(userP.getUserId());
        userInfoP.setCreateBy(userP.getUserId());
        userInfoP.setCreateTime(new Date());
        userInfoP.setCreateDept(userP.getDeptId());
        userInfoP.setUpdateBy(userP.getUserId());
        userInfoP.setUpdateTime(new Date());

        int i1 = userPMapper.insert(userP);
        int i2 = userInfoPMapper.insert(userInfoP);

        if (i1+i2==2){
            JavaMailSenderImpl sender = (JavaMailSenderImpl)mailSender;
            MimeMessage mailMessage = sender.createMimeMessage();
            try {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true);
                messageHelper.setFrom("lyzzz30@126.com");
                // messageHelper.setFrom(currentUser.getUserInfoP().getEmail());
                messageHelper.setTo(userInfoP.getEmail());

                messageHelper.setSubject("欢迎新员工<"+userInfoP.getName()+">");
                String html = "<html>" +
                        " <body>" +
                        "  <h1>欢迎新员工</h1>" +
                        "  <P>您的账号是："+userP.getUserName()+"</p>" +
                        "  <P>您的密码是："+password+"</p>" +
                        " </body>" +
                        "</html>";
                messageHelper.setText(html, true);
                new Thread(() -> sender.send(mailMessage)).start();

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public UserInfoP selectUserInfoById(String id) {
        return userInfoPMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateUserAndUserInfo(UserInfoP userInfoP, UserP userP) {
        userInfoP.setUpdateTime(new Date());
        userP.setUpdateTime(new Date());
        int i1 = userPMapper.updateByPrimaryKeySelective(userP);
        int i2 = userInfoPMapper.updateByPrimaryKeySelective(userInfoP);

        return i1+i2;
    }
    // 删除
    @Override
    public DeleteResult deleteUser(String[] userIds) {

        List<String> userName = new ArrayList<String>();

        for (int i = 0; i < userIds.length; i++) {
            String userId = userIds[i];
            // 判断是不是直属领导
            if(isUserManager(userId)) {
                UserP userP = userPMapper.selectByPrimaryKey(userId);
                userName.add(userP.getUserName());
                continue;
            }
            userInfoPMapper.deleteByPrimaryKey(userId);
            userPMapper.deleteByPrimaryKey(userId);
        }
        if(userName.size()>0){
            return new DeleteResult(400,null,userName);
        }
        return new DeleteResult(200,"删除成功！！！",null);
    }

    //判断是不是直属领导
    private boolean isUserManager(String userId) {
        List<UserInfoP> infoList = userInfoPMapper.selectManager(userId);
        if(infoList!=null && infoList.size()>0){
            return true;
        }
        return false;
    }

}
