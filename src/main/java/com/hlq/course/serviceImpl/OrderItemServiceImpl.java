package com.hlq.course.serviceImpl;

import com.hlq.course.dao.OrderItemMapper;
import com.hlq.course.pojo.OrderItem;
import com.hlq.course.pojo.OrderItemExample;
import com.hlq.course.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Len on 2019-10-08
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public Boolean saveBatch(List<OrderItem> orderItemList) {
        orderItemList.forEach(orderItem -> {
            orderItemMapper.insert(orderItem);
        });
        return Boolean.TRUE;
    }

    @Override
    public List<OrderItem> selectByOrderId(Integer orderId) {
        OrderItemExample example = new OrderItemExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        return orderItemMapper.selectByExample(example);
    }
}
