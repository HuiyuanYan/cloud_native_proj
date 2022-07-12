package com.example.cloud_native_proj.controller;

//引入依赖
import  org.springframework.stereotype.Controller;
import  org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import  org.springframework.web.bind.annotation.RestController;
import  org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.yueshutong.springbootstartercurrentlimiting.annotation.CurrentLimiter;

@RestController
public class UserController {
    //@ResponseBody
    @RequestMapping("/hello")
    //每秒并发量，设置为10
    @CurrentLimiter(QPS = 10)
    public Object sayHello() {
        JSONObject jsonObject=new JSONObject();

        jsonObject.put("code",200);
        jsonObject.put("group","1");
        jsonObject.put("member","Qinyu Chen,Jifeng Duan,Huiyuan Yan");
        jsonObject.put("msg","Welcome!");
        return jsonObject.toString();
    }

}
