package com.hlq.course.controller;

import com.hlq.course.pojo.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Len on 2019-09-18
 */
@Controller
public class MainController {

    @RequestMapping("/")
    public String index(Model model){
        Msg msg =  new Msg("测试标题","测试内容","额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "home";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @ResponseBody
    @RequestMapping("/admin")
    public String admin(){
        return "this is admin!!";
    }

    @ResponseBody
    @RequestMapping("/user")
    public String user(){
        return "this is user!!";
    }


}
