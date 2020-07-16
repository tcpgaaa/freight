package com.lyzzz.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class ExportCVo extends ExportC{
    //货物的数量
    private Integer exportProductNum;
    //附件的数量
    private Integer extEproductNum;
}