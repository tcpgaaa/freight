package com.lyzzz.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import lombok.Data;

import java.io.Serializable;
@Data
@ContentRowHeight(20)
@ColumnWidth(20)
public class DeptPVo implements Serializable {
    @ColumnWidth(10)
    @ExcelProperty("序号")
    private String deptId;
    @ExcelProperty("部门名称")
    private String deptName;
    @ExcelProperty("上级部门编号")
    private String parentId;
    @ExcelProperty("状态")
    private Integer state;
    @ExcelProperty("部门编号")
    private String deptNo;
    @ExcelProperty("上级部门名称")
    private String parentName;

}
