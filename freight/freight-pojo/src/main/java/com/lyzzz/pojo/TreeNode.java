package com.lyzzz.pojo;

import lombok.Data;

/**
 * @location： freight\com.lyzzz.pojo
 * @creatTime: 2020/7/8  11:22
 * @author: Administrator
 * @remark:
 */
@Data
public class TreeNode {
    private String id;
    private String pId;
    private String name;
    private Boolean open=true;
    private Boolean checked;
    private Integer layerNum;

}
