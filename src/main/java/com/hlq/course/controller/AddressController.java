package com.hlq.course.controller;

import com.hlq.course.common.Message;
import com.hlq.course.pojo.Address;
import com.hlq.course.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Len on 2019-10-05
 */
@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @RequestMapping("/list/{username}")
    public Message getAddressList(@PathVariable String username){
        List<Address> list = addressService.getAddListByUser(username);
        return Message.success(list);
    }
    @RequestMapping("/add/{username}")
    public Message addAddress(@RequestBody Address address,@PathVariable String username){
        addressService.addAddress(address,username);
        return Message.success(Boolean.TRUE);
    }
}
