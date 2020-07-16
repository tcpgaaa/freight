package com.lyzzz.controller;

import com.alibaba.excel.EasyExcel;
import com.lyzzz.pojo.DeleteResult;
import com.lyzzz.pojo.DeptP;
import com.lyzzz.pojo.DeptPVo;
import com.lyzzz.pojo.PageBean;
import com.lyzzz.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping("/exportDept")
    public void exportDept(PageBean pageBean, HttpServletResponse response) throws IOException {
        //1、查询要导出的数据
        PageBean pb = deptService.listDeptOfPage(pageBean);

        List<DeptPVo> datas = (List<DeptPVo>) pb.getDatas();

        response.setContentType("application/vnd.ms-excel");
        String fileName = URLEncoder.encode("部门数据", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        EasyExcel.write(response.getOutputStream(), DeptPVo.class).sheet("sheet1").doWrite(datas);

    }

    @RequestMapping(value = "/list")
    // 查询所有部门信息及上级部门名称
    public String listDeptAndParent(Model model, PageBean pageBean) {
        PageBean pb = deptService.listDeptOfPage(pageBean);
        model.addAttribute("pb", pb);
        return "sysadmin/dept/jDeptList";
    }

    @RequestMapping(value = "/toCreate")
    // 添加
    public String toCreate(Model model) {
        //查询所有部门，返回部门下拉列表
        List<DeptP> deptPList = deptService.listDept();
        model.addAttribute("depts", deptPList);
        return "sysadmin/dept/jDeptCreate";
    }

    @RequestMapping("/create")
    // 保存数据
    public String insert(DeptP deptP) {
        //把deptP保存到数据库
        deptService.addDept(deptP);
        //重新查询最新的数据
        return "redirect:/dept/list";
    }

    @RequestMapping("/toView")
    // 查看
    public String toView(String id, Model model) {
        DeptPVo deptP = deptService.DeptAndParentById(id);
        model.addAttribute("dept", deptP);
        return "sysadmin/dept/jDeptView";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id, Model model) {
        DeptP deptP = deptService.getDeptById(id);
        model.addAttribute("dept",deptP);
        List<DeptP> deptPList = deptService.listDept();
        model.addAttribute("depts",deptPList);
        return "sysadmin/dept/jDeptUpdate";
    }

    @RequestMapping("/update")
    // 修改
    public String update(DeptP deptP) {
        deptService.updateDept(deptP);
        //重新查询最新的数据
        return "redirect:/dept/list";
    }
    @RequestMapping("/delete")
    // 删除
    @ResponseBody
    public DeleteResult delete (String[] deptId){
        DeleteResult deleteResult = deptService.deleteDeptByDeptId(deptId);
        return deleteResult;
    }



}
