package com.lyzzz.pojo;

import lombok.Data;

import java.util.List;
@Data
public class PageBean {
    //数据
    private List<?> datas;

    //总共多少条
    private Long totalRows;

    //当前页
    private Integer curPage=1;

    //每页多少条数据
    private Integer pageSize=10;

    //总共多少页
    private Integer totalPages;

}
