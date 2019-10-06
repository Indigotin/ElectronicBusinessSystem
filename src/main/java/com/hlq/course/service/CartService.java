package com.hlq.course.service;

import com.hlq.course.pojo.ItemCart;

import java.util.List;

/**
 * Created by Len on 2019-10-05
 */
public interface CartService {
    List<ItemCart> getCartListByUserId(Integer userId);
    Boolean deleteByUserIdAndItemId(Integer userId,Integer itemId);
    Boolean addCart(ItemCart cart);
    Boolean updateCart(Integer userId,Integer itemId,Integer quantity);
    Boolean clean(List<Integer> cartIds);
}
