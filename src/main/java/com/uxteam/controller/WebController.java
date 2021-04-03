package com.uxteam.controller;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    /**
     * 主页访问接口
     * @return 主页页面
     */
    @RequestMapping("/")
    public String index(){

        return "index";
    }
}
