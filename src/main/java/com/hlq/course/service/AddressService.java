package com.hlq.course.service;

import com.hlq.course.pojo.Address;

import java.util.List;

/**
 * Created by Len on 2019-10-05
 */
public interface AddressService {
    List<Address> getAddListByUser(String username);
    Boolean addAddress(Address address,String username);
}
