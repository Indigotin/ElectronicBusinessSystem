package com.hlq.course.service;

import com.github.pagehelper.PageInfo;
import com.hlq.course.model.OrderModel;

import java.util.List;

/**
 * Created by Len on 2019-10-08
 */
public interface OrderService {
    Boolean createOrder(OrderModel orderModel);
    PageInfo<OrderModel> getPageByUserId(Integer userId, Integer status, Integer current, Integer size);
    List<OrderModel> getOrderByUserId(Integer userIdr);
    PageInfo<OrderModel> getAllOrder(Integer current, Integer size);
}
