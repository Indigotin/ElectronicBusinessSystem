package com.hlq.course.controller;

import com.hlq.course.common.Message;
import com.hlq.course.pojo.ItemCart;
import com.hlq.course.pojo.User;
import com.hlq.course.service.CartService;
import com.hlq.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Len on 2019-10-05
 */
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    @RequestMapping("/list/{username}")
    public Message getCartList(@PathVariable("username")String username){
        User user = userService.selectByUsername(username);
        List<ItemCart> list = cartService.getCartListByUserId(user.getId());
        return Message.success(list);
    }

    @RequestMapping("/delete/{username}/{cartId}")
    public Message deleteCart(@PathVariable("username")String username,@PathVariable("cartId")Integer cartId){
        User user = userService.selectByUsername(username);
        if(cartService.deleteByUserIdAndCartId(user.getId(),cartId)){
            return Message.success(Boolean.TRUE);
        }
        return Message.failed();
    }

    @RequestMapping("/add")
    public Message addCart(@RequestBody ItemCart cart){
        if(cartService.addCart(cart)){
            return Message.success(Boolean.TRUE);
        }
        return Message.failed();
    }


    @RequestMapping("/update/{username}/{itemId}/{quantity}")
    public Message updateCart(@PathVariable("username")String username,
                              @PathVariable("itemId")Integer itemId,@PathVariable("quantity")Integer quantity){
        User user = userService.selectByUsername(username);
        cartService.updateCart(user.getId(),itemId,quantity);
        return Message.success(Boolean.TRUE);
    }
    @RequestMapping("/clean")
    public Message cleanCart(@RequestBody List<Integer> cartIds){
        cartService.clean(cartIds);
        return Message.success(Boolean.TRUE);
    }
}
