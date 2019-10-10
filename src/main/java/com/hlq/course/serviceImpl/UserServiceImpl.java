package com.hlq.course.serviceImpl;

import com.hlq.course.dao.UserMapper;
import com.hlq.course.pojo.User;
import com.hlq.course.pojo.UserExample;
import com.hlq.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Len on 2019-09-20
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public Boolean login(User user){
        String username = user.getUsername();
        String password = user.getPassword();
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> list = userMapper.selectByExample(userExample);
        return list.get(0).getPassword().equals(password);
    }

    @Override
    public User selectByUsername(String username) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        return userMapper.selectByExample(example).get(0);
    }

    @Override
    public Boolean register(User user) {
        return userMapper.insert(user)==1;
    }

    @Override
    public String getUsername(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user.getUsername();
    }
}
