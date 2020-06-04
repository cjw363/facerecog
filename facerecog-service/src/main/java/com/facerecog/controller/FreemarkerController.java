package com.facerecog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @Classname FreemarkerController
 * @Description
 * @Date 2020/6/4 13:43
 * @Created by cjw
 */
@RequestMapping("/freemarker")
@Controller
public class FreemarkerController {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/test")
    public String test(Map<String,Object> map){
        map.put("name","小明");
        return "test";//返回模板文件名字
    }
}
