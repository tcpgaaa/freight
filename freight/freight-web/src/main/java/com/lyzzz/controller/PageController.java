package com.lyzzz.controller;

import com.lyzzz.pojo.CurrentUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    //欢迎页
    @RequestMapping("/index")
    public String index(){
        return "index";
    }


    //跳转主页
    @RequestMapping("/main")
    public String main(){
        return "home/fmain";
    }
    //查询
    @RequestMapping("/actionList")
    public String actionList(){
        return "redirect:/dept/list";
    }

    //标题栏菜单
    @RequestMapping("/homeAction_totitle")
    public String homeAction_totitle(Model model){
        Subject subject = SecurityUtils.getSubject();
        CurrentUser currentUser = (CurrentUser) subject.getPrincipal();
        model.addAttribute("currentUser",currentUser);

        return "home/title";
    }

    //跳转左侧菜单
    @RequestMapping("homeAction_toleft")
    public String homeAction_left(String moduleName) throws Exception {
        return moduleName+"/left";
    }

    //跳转主窗口
    @RequestMapping("homeAction_tomain")
    public String homeAction_main(String moduleName) throws Exception {
        return moduleName + "/main";
    }
    //跳转主窗口
    @RequestMapping("toMain")
    public String toMain(String moduleName) throws Exception {
//        return moduleName + "/main";
        return "orders/orderTaskList";
    }

}
