package com.lyzzz.pojo;

import lombok.Data;

//ztree的bean对象
@Data
public class TreeNode {
    private String id;
    private String pId;
    private String name;
    private Boolean open=true;
    private Boolean checked;
    private Integer layerNum;

}
