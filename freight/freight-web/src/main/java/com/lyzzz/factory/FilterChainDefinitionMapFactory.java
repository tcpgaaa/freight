package com.lyzzz.factory;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapFactory {

    public LinkedHashMap<String,String> buildFilterChainDefinitionMap(){

        LinkedHashMap<String,String> map = new LinkedHashMap<>();

        //map.put("/","anon");
        map.put("/css/**","anon");
        map.put("/images/**","anon");
        map.put("/js/**","anon");
        map.put("/skin/**","anon");
        map.put("/components/**","anon");
        map.put("/user/login","anon");
        map.put("/index","anon");
        map.put("/logout","logout");
        map.put("/**","authc");    //  记住我  和 认证的区别
        return map;
    }
}
