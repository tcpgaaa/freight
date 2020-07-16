package com.lyzzz.controller;

import com.alibaba.excel.EasyExcel;
import com.lyzzz.pojo.*;
import com.lyzzz.realm.CustomRealm;
import com.lyzzz.service.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RolePService rolePService;
    @Autowired
    private RoleUserPService roleUserPService;
    @Autowired
    private CustomRealm customRealm;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, @RequestParam("userName") String account, @RequestParam("password") String password) {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(account, password);
            //token.setRememberMe(true);
            try {
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                log.info("账号错误" + token.getPrincipal());
                model.addAttribute("errorInfo", "账号不存在！！！");
            } catch (IncorrectCredentialsException ice) {
                log.info("密码 " + token.getPrincipal() + " 错误 ");
                model.addAttribute("errorInfo", "密码错误！！！");
            } catch (LockedAccountException lae) {
                log.info("账号 " + token.getPrincipal() + " 被锁定  " +
                        "请联系管理员！");
                SecurityUtils.getSubject().getSession().setAttribute("errorInfo", "被锁定，请联系管理员！！！！！！");
            } catch (AuthenticationException ae) {
                System.out.println("登陆失败");
            }
        }

        return "redirect:/main";
    }

    @RequestMapping("/list")
    // 列表
    public String list(PageBean pageBean, Model model) {
        PageBean pageBean1 = userService.listUserOfPage(pageBean);
        model.addAttribute("pb", pageBean1);
        return "sysadmin/user/jUserList";
    }

    @RequestMapping("/toView")
    // 查看
    public String toView(String id, Model model) {
        UserP userP = userService.selectUserById(id);
        model.addAttribute("u", userP);
        return "sysadmin/user/jUserView";
    }

    @RequestMapping(value = "/toCreate")
    // 添加
    public String toCreate(Model model) {
        //查询所有部门
        List<DeptP> deptPList = deptService.listDept();
        model.addAttribute("deptPList", deptPList);
        //查询直属领导
        List<UserInfoP> userInfoPList = userService.selectUserInfo();
        model.addAttribute("userInfoPList", userInfoPList);
        return "sysadmin/user/jUserCreate";
    }

    @RequestMapping("/create")
    // 保存数据
    public String insert(UserInfoP userInfoP, UserP userP) {
        System.out.println(userInfoP);
        System.out.println(userP);
        userService.addUser(userP, userInfoP);
        return "redirect:/user/list";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id, Model model) {
        UserP userP = userService.selectUserById(id);
        model.addAttribute("u", userP);
        UserInfoP userInfoP = userService.selectUserInfoById(id);
        model.addAttribute("uf", userInfoP);
        List<DeptP> deptPList = deptService.listDept();
        model.addAttribute("ds", deptPList);
        List<UserInfoP> userInfoPList = userService.selectUserInfo();
        model.addAttribute("uis", userInfoPList);
        return "sysadmin/user/jUserUpdate";
    }

    @RequestMapping("/update")
    // 修改
    public String update(UserInfoP userInfoP, UserP userP) {
        int i = userService.updateUserAndUserInfo(userInfoP, userP);
        //重新查询最新的数据
        return "redirect:/user/list";
    }
    @RequestMapping("/toRole")
    public String toRole(String id, Model model){
        model.addAttribute("id",id);
        // 查询用户扩展信息
        UserInfoP userInfoP = userInfoService.getUserInfoById(id);
        model.addAttribute("userInfoP",userInfoP);

        // 查询所有角色
        List<RoleP> rolePList = rolePService.selectAll();
        model.addAttribute("rolePList",rolePList);

        // 查询当前用户的角色
        List<RoleUserP> roleUserPList = roleUserPService.listRoleUser(id);
        // 拼接userID
        String[] roleIdsArr = new String[roleUserPList.size()];
        for (int i=0;i<roleUserPList.size();i++){
            RoleUserP roleUserP = roleUserPList.get(i);
            roleIdsArr[i] = roleUserP.getUserId();
        }
        String roleIds = StringUtils.join(roleIdsArr, ",");
        model.addAttribute("roleids",roleIds);
        return "sysadmin/user/jUserRole";
    }
    @RequestMapping("/role")
    public String role(String id, String[] roleId){
        // 保存数据
        roleUserPService.addRoleUser(id,roleId);
        // 清空缓存
        customRealm.clearCached();
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public DeleteResult delete(String[] userIds){
        DeleteResult deleteResult = userService.deleteUser(userIds);
        return deleteResult;
    }

    @RequestMapping("/exportUser")
    public void exportDept(PageBean pageBean, HttpServletResponse response) throws IOException {
        //1、查询要导出的数据
        PageBean pageBean1 = userService.listUserOfPage(pageBean);

        List<UserP> datas = (List<UserP>) pageBean1.getDatas();

        response.setContentType("application/vnd.ms-excel");
        String fileName = URLEncoder.encode("用户数据", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        EasyExcel.write(response.getOutputStream(), UserP.class).sheet("sheet1").doWrite(datas);

    }

}
