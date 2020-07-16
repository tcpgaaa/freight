package com.lyzzz.pojo;

import java.io.Serializable;
import lombok.Data;

/**
@location：  freight\com.lyzzz.pojo  
@creatTime:   2020/7/9  13:23
@author:  Administrator
@remark:

*/
@Data
public class RoleModuleP implements Serializable {
    private String moduleId;

    private String roleId;

    private static final long serialVersionUID = 1L;
}