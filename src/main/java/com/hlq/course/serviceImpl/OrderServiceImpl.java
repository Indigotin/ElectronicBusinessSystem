package com.hlq.course.serviceImpl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hlq.course.common.OrderConstant;
import com.hlq.course.dao.OrderInfoMapper;
import com.hlq.course.model.OrderModel;
import com.hlq.course.pojo.OrderInfo;
import com.hlq.course.pojo.OrderInfoExample;
import com.hlq.course.pojo.OrderItem;
import com.hlq.course.service.OrderItemService;
import com.hlq.course.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Len on 2019-10-08
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderInfoMapper orderMapper;
    @Autowired
    private OrderItemService orderItemService;

    @Override
    public Boolean createOrder(OrderModel orderModel) {
        OrderInfo order = new ModelMapper().map(orderModel, OrderInfo.class);
        order.setStatus(OrderConstant.UN_PAID);
        order.setPaymentTime(new Date());
        order.setPaymentType(1);
        //是否返回主键？???
        order.setId(orderMapper.insert(order));
        List<OrderItem> orderItemList = orderModel.getOrderItemList();
        orderItemList.forEach(orderItem -> {
            orderItem.setOrderId(order.getId());
            orderItem.setAddressId(order.getAddressId());
            orderItem.setUserId(order.getUserId());
        });
        orderItemService.saveBatch(orderItemList);
        return Boolean.TRUE;
    }

    @Override
    public PageInfo<OrderModel> getPageByUserId(Integer userId, Integer status, Integer current, Integer size) {
        Page<OrderInfo> page = PageHelper.startPage(current,size,true);
        OrderInfoExample example = new OrderInfoExample();
        //status未设置条件
        example.createCriteria().andUserIdEqualTo(userId);
        orderMapper.selectByExample(example);
        PageInfo pageInfo =  page.toPageInfo();
        pageInfo.setList(page.getResult()
                .stream()
                .parallel()
                .map(orderInfo -> {
                    OrderModel orderModel = new OrderModel();
                    BeanUtil.copyProperties(orderInfo,orderModel);
//                    orderModel.setOrderItemList(orderItemService.selectByOrderId(orderModel.getId()));
                    orderModel.setOrderItemList(Collections.emptyList());
                    return orderModel;
                })
                .collect(Collectors.toList()));
        return pageInfo;
    }

    @Override
    public List<OrderModel> getOrderByUserId(Integer userId) {
        OrderInfoExample example = new OrderInfoExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<OrderModel> models = orderMapper.selectByExample(example).stream().map(model->new ModelMapper().map(model,OrderModel.class)).collect(Collectors.toList());
        models.forEach(orderModel -> {
            List<OrderItem> orderDetails = orderItemService.selectByOrderId(orderModel.getId());
            orderModel.setOrderItemList(orderDetails);
        });
        return models;
    }
}
