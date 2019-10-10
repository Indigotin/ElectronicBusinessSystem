package com.hlq.course.service;

import com.hlq.course.pojo.User;

/**
 * Created by Len on 2019-09-26
 */
public interface UserService {
    Boolean login(User user);
    User selectByUsername(String username);
    Boolean register(User user);
    String getUsername(Integer id);
}
