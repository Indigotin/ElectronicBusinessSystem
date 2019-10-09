package com.hlq.course.service;

import com.hlq.course.pojo.OrderItem;

import java.util.List;

/**
 * Created by Len on 2019-10-08
 */
public interface OrderItemService {
    Boolean saveBatch(List<OrderItem> orderItemList);
    List<OrderItem> selectByOrderId(Integer orderId);
}
