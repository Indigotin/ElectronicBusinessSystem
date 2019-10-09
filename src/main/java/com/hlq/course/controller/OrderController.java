package com.hlq.course.controller;

import com.github.pagehelper.PageInfo;
import com.hlq.course.common.Message;
import com.hlq.course.model.OrderModel;
import com.hlq.course.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Len on 2019-09-25
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(path = "/create")
    public Message create(@RequestBody OrderModel orderModel) {
        Boolean res = orderService.createOrder(orderModel);
        if (res) {
            return Message.success();
        }
        return Message.failed();
    }

    @RequestMapping("/list/{userId}/{op}/{cur}/{size}")
    public Message getOrderList(@PathVariable("userId")Integer userId,@PathVariable("op")Integer op,@PathVariable("cur")Integer cur,@PathVariable("size")Integer size){
        PageInfo<OrderModel> pageInfo = orderService.getPageByUserId(userId,op,cur,size);
        return Message.success(pageInfo);
    }

    @RequestMapping(value = "/getOrders/{userId}")
    public Message getOrders(@PathVariable("userId")Integer userId){
        List<OrderModel> modelList = orderService.getOrderByUserId(userId);
        return Message.success(modelList);
    }
}
