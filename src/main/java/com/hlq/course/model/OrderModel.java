package com.hlq.course.model;

import com.hlq.course.pojo.OrderInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Len on 2019-10-08
 */
public class OrderModel extends OrderInfo implements Serializable {

    private static final long serialVersionUID = -2669286586484973878L;

    private String username;

    private List<OrderItemModel> orderItemList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<OrderItemModel> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItemModel> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
