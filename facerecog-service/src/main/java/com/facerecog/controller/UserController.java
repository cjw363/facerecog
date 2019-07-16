package com.facerecog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Classname UserController
 * @Description
 * @Date 2019/7/16 15:16
 * @Created by cjw
 */
@Controller
@RequestMapping("user")
public class UserController {

    @PostMapping("login")
    @ResponseBody
    public String login(){
        return "haha";
    }
}
