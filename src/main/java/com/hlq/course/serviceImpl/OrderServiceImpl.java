package com.hlq.course.serviceImpl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hlq.course.common.OrderConstant;
import com.hlq.course.common.SendMessage;
import com.hlq.course.dao.OrderInfoMapper;
import com.hlq.course.model.OrderItemModel;
import com.hlq.course.model.OrderModel;
import com.hlq.course.pojo.Item;
import com.hlq.course.pojo.OrderInfo;
import com.hlq.course.pojo.OrderInfoExample;
import com.hlq.course.pojo.OrderItem;
import com.hlq.course.service.ItemSevice;
import com.hlq.course.service.OrderItemService;
import com.hlq.course.service.OrderService;
import com.hlq.course.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    @Autowired
    private ItemSevice itemSevice;
    @Autowired
    private UserService userService;

    @Override
    public Boolean createOrder(OrderModel orderModel) {
        OrderInfo order = new ModelMapper().map(orderModel, OrderInfo.class);
        order.setStatus(OrderConstant.UN_PAID);
        order.setPaymentTime(new Date());
        order.setPaymentType(1);
        orderMapper.insert(order);
        List<OrderItem> orderItemList = toOrderItems(orderModel.getOrderItemList());
        orderItemList.forEach(orderItem -> {
            orderItem.setOrderId(order.getId());
            orderItem.setAddressId(order.getAddressId());
            orderItem.setUserId(order.getUserId());
        });
        orderItemService.saveBatch(orderItemList);
        SendMessage smg = new SendMessage();
        smg.sendOrderMessage(orderItemList);
        return Boolean.TRUE;
    }

    @Override
    public PageInfo<OrderModel> getPageByUserId(Integer userId, Integer status, Integer current, Integer size) {
        Page<OrderInfo> page = PageHelper.startPage(current,size,true);
        OrderInfoExample example = new OrderInfoExample();
        //status未设置条件
        example.createCriteria().andUserIdEqualTo(userId);
        orderMapper.selectByExample(example);
        PageInfo pageInfo = page.toPageInfo();
        pageInfo.setList(toOrderModels(page.getResult()));
        return pageInfo;
    }

    @Override
    public List<OrderModel> getOrderByUserId(Integer userId) {
        OrderInfoExample example = new OrderInfoExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<OrderModel> models = toOrderModels(orderMapper.selectByExample(example));
        return models;
    }

    @Override
    public PageInfo<OrderModel> getAllOrder(Integer current, Integer size) {
        Page<OrderInfo> page = PageHelper.startPage(current,size,true);
        orderMapper.selectByExample(new OrderInfoExample());
        PageInfo pageInfo = page.toPageInfo();
        pageInfo.setList(toOrderModels(page.getResult()));
        return pageInfo;
    }

    @Override
    public Boolean editStatus(OrderModel orderModel) {
        OrderInfo order = new ModelMapper().map(orderModel, OrderInfo.class);
        orderMapper.updateByPrimaryKeySelective(order);
        return Boolean.TRUE;
    }

    @Override
    public PageInfo<OrderModel> getSearchList(String orderId, Integer current, Integer size) {
        Page<OrderInfo> page = PageHelper.startPage(current, size, true);
        OrderInfoExample example = new OrderInfoExample();
        if (orderId != null && !orderId.equals("") && !orderId.equals("undefined")) {
            example.createCriteria().andIdEqualTo(Integer.valueOf(orderId));
        }
        orderMapper.selectByExample(example);
        PageInfo pageInfo = page.toPageInfo();
        pageInfo.setList(toOrderModels(page.getResult()));
        return pageInfo;
    }

    private List<OrderModel> toOrderModels(List<OrderInfo> orderInfos){

        return orderInfos.stream()
                .parallel()
                .map(orderInfo -> {
                    OrderModel orderModel = new OrderModel();
                    BeanUtil.copyProperties(orderInfo,orderModel);
                    orderModel.setUsername(userService.getUsername(orderInfo.getUserId()));
                    orderModel.setOrderItemList(toOrderItemModels(orderItemService.selectByOrderId(orderModel.getId())));
                    return orderModel;
                })
                .collect(Collectors.toList());
    }

    private List<OrderItem> toOrderItems(List<OrderItemModel> models){
        return models.stream()
                .parallel()
                .map(model->{
                    OrderItem orderItem = new OrderItem();
                    BeanUtil.copyProperties(model,orderItem);
                    return orderItem;
                }).collect(Collectors.toList());
    }

    private List<OrderItemModel> toOrderItemModels(List<OrderItem> orderItems){
        return orderItems
                .stream()
                .parallel()
                .map(orderItem->{
                    OrderItemModel model = new OrderItemModel();
                    BeanUtil.copyProperties(orderItem,model);
                    Item item = itemSevice.getById(model.getItemId());
                    model.setItemImage(item.getImage());
                    model.setItemName(item.getName());
                    return model;
                }).collect(Collectors.toList());

    }

}
