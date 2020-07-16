package com.lyzzz.controller;

import com.lyzzz.pojo.DeleteResult;
import com.lyzzz.pojo.PageBean;
import com.lyzzz.pojo.RoleP;
import com.lyzzz.realm.CustomRealm;
import com.lyzzz.service.RoleModulePService;
import com.lyzzz.service.RolePService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyzzz.pojo.TreeNode;
import java.util.List;

/**
 * @location： freight\com.lyzzz.controller
 * @creatTime: 2020/7/8  11:03
 * @author: Administrator
 * @remark:
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RolePService rolePService;
    @Autowired
    private RoleModulePService roleModulePService;
    @Autowired
    private CustomRealm customRealm;

    @RequestMapping("/list")
    public String listRole(PageBean pageBean, Model model){
        PageBean pb = rolePService.listRoleOfPage(pageBean);
        model.addAttribute("pb",pb);
        return "sysadmin/role/jRoleList";
    }
    @RequestMapping("/toView")
    // 查看
    public String toView(String id, Model model) {
        RoleP roleP = rolePService.selectByPrimaryKey(id);
        model.addAttribute("role", roleP);
        return "sysadmin/role/jRoleView";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id, Model model) {
        RoleP roleP = rolePService.selectByPrimaryKey(id);
        model.addAttribute("role",roleP);

        return "sysadmin/role/jRoleUpdate";
    }

    @RequestMapping("/update")
    // 修改
    public String update(RoleP roleP) {
        rolePService.updateByPrimaryKeySelective(roleP);
        //重新查询最新的数据
        return "redirect:/role/list";
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public DeleteResult delete(String[] roleIds){
        DeleteResult deleteResult = rolePService.deleteRole(roleIds);
        return deleteResult;
    }

    @RequestMapping(value = "/toCreate")
    // 添加
    public String toCreate() {
        return "sysadmin/role/jRoleCreate";
    }

    @RequestMapping("/create")
    // 保存数据
    public String create(RoleP roleP) {
        rolePService.insertSelective(roleP);
        return "redirect:/role/list";
    }

    @RequestMapping("/toModule")
    public String toModule(String id, Model model){
        RoleP roleP = rolePService.selectByPrimaryKey(id);
        model.addAttribute("role",roleP);
        return "sysadmin/role/jRoleModule";
    }


    //查询zTree的数据
    @RequestMapping("/listModuleOfTreeBeanByRoleId")
    @ResponseBody
    public List<TreeNode> listModuleOfTreeBeanByRoleId(String roleid) {
        List<TreeNode> treeNodes = rolePService.listModuleOfTreeBeanByRoleId(roleid);
        return treeNodes;
    }

    //权限分配
    @RequestMapping("/role")
    public String createRoleModule(String roleId,String[] moduleIds,Model model) throws Exception {

        roleModulePService.createRoleModule(roleId,moduleIds);
        //清空缓存
        customRealm.clearCached();
        return "redirect:/role/list";
    }
}


