package com.lyzzz.controller;

import com.lyzzz.pojo.*;
import com.lyzzz.service.ModulePService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @location： freight\com.lyzzz.controller
 * @creatTime: 2020/7/9  18:48
 * @author: Administrator
 * @remark:
 */
@Controller
@RequestMapping("/module")
public class ModuleController {
    @Autowired
    private ModulePService modulePService;


    @RequestMapping("/list")
    public String list(PageBean pageBean, Model model){
        PageBean pb = modulePService.listRoleOfPage(pageBean);
        model.addAttribute("pb",pb);

        return "sysadmin/module/jModuleList";
    }

    @RequestMapping("/toView")
    // 查看
    public String toView(String id, Model model) {
        ModuleP moduleP = modulePService.selectByPrimaryKey(id);
        System.out.println(moduleP);
        model.addAttribute("module", moduleP);
        return "sysadmin/module/jModuleView";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id, Model model) {
        ModuleP moduleP = modulePService.selectByPrimaryKey(id);
        model.addAttribute("module", moduleP);
        List<ModuleP> modulePList = modulePService.selectAll();
        model.addAttribute("ms", modulePList);
        return "sysadmin/module/jModuleUpdate";
    }

    @RequestMapping("/update")
    // 修改
    public String update(ModuleP moduleP) {
        modulePService.updateByPrimaryKeySelective(moduleP);
        //重新查询最新的数据
        return "redirect:/module/list";
    }

    @RequestMapping(value = "/delete")
    public String delete(String id){
        modulePService.deleteByPrimaryKey(id);
        return "redirect:/module/list";
    }

    @RequestMapping("/toCreate")
    public String tocreate() {
        return "sysadmin/module/jModuleCreate";
    }
    @RequestMapping("/create")
    // 保存数据
    public String insert(ModuleP moduleP) {
        modulePService.insert(moduleP);
        return "redirect:/module/list";
    }

    //查询zTree的数据
    @RequestMapping("/listModuleOfTreeBean")
    @ResponseBody
    public List<TreeNode> listModuleOfTreeBean() {
        List<TreeNode> treeNodes = modulePService.listModuleOfTreeBean();
        return treeNodes;
    }




}
