package com.example.cloud_native_proj.FC;

import cn.yueshutong.springbootstartercurrentlimiting.annotation.CurrentLimiter;
import cn.yueshutong.springbootstartercurrentlimiting.handler.CurrentAspectHandler;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

/*
流量繁忙的处理方法
*/
@Component
public class MyCurrentLimitHandler  implements CurrentAspectHandler {
    @Override
    public Object around(ProceedingJoinPoint pjp, CurrentLimiter rateLimiter)  {

        //限流的返回数据可以自己根据需求场景设计

        JSONObject jsonObject=new JSONObject();

        jsonObject.put("code",429);
        jsonObject.put("msg","Too Too many requests");
        return jsonObject.toString();
        //return "Too many!!!";
    }
}
