package com.lyzzz.controller;

import com.lyzzz.pojo.PackingListC;
import com.lyzzz.pojo.PageBean;
import com.lyzzz.service.ExportCService;
import com.lyzzz.service.PackingListCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @location： freight\com.lyzzz.controller
 * @creatTime: 2020/7/13  14:25
 * @author: Administrator
 * @remark:
 */
@Controller
@RequestMapping("/packingList")
public class PackingListController {
    @Autowired
    private PackingListCService packingListCService;

    @Autowired
    private ExportCService exportCService;


    @RequestMapping("/list")
    public String listPackingListOfPage(PageBean pageBean, Model model){
        PageBean pb = packingListCService.listPackingListOfPage(pageBean);
        pb.getDatas().stream()
                .forEach(System.out::println);
        model.addAttribute("pb",pb);
        return "cargo/packinglist/jPackingListListPage";
    }

    //跳转装箱管理的新增页面
    @RequestMapping("/toCreate")
    public String toCreate(PageBean pageBean,Model model){
        //分页查询报运单列表
        PageBean pb = exportCService.listExportOfPage(pageBean,1);
        model.addAttribute("pb",pb);
        return "cargo/packinglist/jPackingListCreate";
    }

    @RequestMapping("/create")
    public String createPackingList(String[] exportId, PackingListC packingListC){
        packingListCService.createPackingList(exportId,packingListC);
        return "redirect:/packingList/list";
    }

    @RequestMapping("/submit")
    public String submitPackingList(String packingListId){
        packingListCService.submitPackingList(packingListId);
        return "redirect:/packingList/list";
    }
    @RequestMapping("/cancel")
    public String cancelPackingList(String packingListId){
        packingListCService.cancelPackingList(packingListId);
        return "redirect:/packingList/list";
    }

    @RequestMapping("/toView")
    public String toView(String packingListId, Model model){
        PackingListC packingListC = packingListCService.selectByPrimaryKey(packingListId);
        model.addAttribute("pack",packingListC);
        return "cargo/packinglist/jPackingListView";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String packingListId, Model model){
        PackingListC packingListC = packingListCService.selectByPrimaryKey(packingListId);
        model.addAttribute("pack",packingListC);
        return "cargo/packinglist/jPackingListUpdate";
    }

    @RequestMapping("/update")
    public String update(PackingListC packingListC){
        packingListCService.updateByPrimaryKeySelective(packingListC);
        return "redirect:/packingList/list";
    }
    @RequestMapping("/delete")
    public String delete(String packingListId){
        packingListCService.deleteByPrimaryKey(packingListId);
        return "redirect:/packingList/list";
    }




}
