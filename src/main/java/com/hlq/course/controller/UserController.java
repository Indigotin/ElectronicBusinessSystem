package com.hlq.course.controller;

import com.hlq.course.common.Message;
import com.hlq.course.pojo.Admin;
import com.hlq.course.pojo.User;
import com.hlq.course.service.AdminService;
import com.hlq.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Len on 2019-09-25
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public Message login(@RequestBody User user){
        boolean flag = userService.login(user);
        user.setPassword("");
        if(flag){
            System.out.println(user.getUsername());
            return Message.success(user.getUsername());
        }
        return Message.failed();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Message register(@RequestBody User user){
        try {
            if (userService.register(user)){
                return Message.success(Boolean.TRUE);
            }
        }catch (Exception e){
            return Message.failed();
        }
        return Message.failed();
    }

    @RequestMapping("/userInfo/{username}")
    public Message getUserInfo(@PathVariable("username")String username){
        User user = userService.selectByUsername(username);
        return Message.success(user);
    }

    @RequestMapping("/amdin/check")
    public Message adminLogin(@RequestBody Admin admin){
        if(adminService.adminLogin(admin.getUsername(),admin.getPassword())){
            return Message.success();
        }
        return Message.failed();
    }
}
