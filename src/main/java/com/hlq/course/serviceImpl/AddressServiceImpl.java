package com.hlq.course.serviceImpl;

import com.hlq.course.dao.AddressMapper;
import com.hlq.course.pojo.Address;
import com.hlq.course.pojo.AddressExample;
import com.hlq.course.pojo.User;
import com.hlq.course.service.AddressService;
import com.hlq.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Len on 2019-10-05
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private UserService userService;

    @Override
    public List<Address> getAddListByUser(String username) {
        User user = userService.selectByUsername(username);
        AddressExample example = new AddressExample();
        example.createCriteria().andUserIdEqualTo(user.getId());
        return addressMapper.selectByExample(example);
    }

    @Override
    public Boolean addAddress(Address address,String username) {
        User user = userService.selectByUsername(username);
        address.setUserId(user.getId());
        addressMapper.insert(address);
        return Boolean.TRUE;
    }
}
