package com.lyzzz.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @location： freight\com.lyzzz.pojo
 * @creatTime: 2020/7/9  18:58
 * @author: Administrator
 * @remark:
 */
@Data
public class ModuleP implements Serializable {
    private String moduleId;

    private String parentId;

    private String parentName;

    private String name;

    private Integer layerNum;

    private Integer isLeaf;

    private String ico;

    private String cpermission;

    private String curl;

    /**
     * 1主菜单/2左侧菜单/3按钮/4链接/5状态
     */
    private Integer ctype;

    /**
     * 1启用0停用
     */
    private Integer state;

    /**
     * 按钮时，可以标识其归属，
     * 查询某页面按钮时就用此属性查询
     */
    private String belong;

    private String cwhich;

    private Integer quoteNum;

    private String remark;

    private Integer orderNo;

    private String createBy;

    private String createDept;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}