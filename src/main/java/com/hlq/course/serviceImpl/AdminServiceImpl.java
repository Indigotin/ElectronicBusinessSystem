package com.hlq.course.serviceImpl;

import com.hlq.course.dao.AdminMapper;
import com.hlq.course.pojo.Admin;
import com.hlq.course.pojo.AdminExample;
import com.hlq.course.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Len on 2019-10-09
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Boolean adminLogin(String username, String password) {
        AdminExample example = new AdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<Admin> list = adminMapper.selectByExample(example);
        if(list.get(0).getPassword().equals(password)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
