package com.hlq.course.serviceImpl;

import com.hlq.course.dao.ItemCartMapper;
import com.hlq.course.pojo.Item;
import com.hlq.course.pojo.ItemCart;
import com.hlq.course.pojo.ItemCartExample;
import com.hlq.course.pojo.User;
import com.hlq.course.service.CartService;
import com.hlq.course.service.ItemSevice;
import com.hlq.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Len on 2019-10-05
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private ItemCartMapper cartMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private ItemSevice itemSevice;

    @Override
    public List<ItemCart> getCartListByUserId(Integer userId) {
        ItemCartExample example = new ItemCartExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<ItemCart> list = cartMapper.selectByExample(example);
        return list;
    }

    @Override
    public Boolean deleteByUserIdAndCartId(Integer userId, Integer cartId) {
        int res = cartMapper.deleteByPrimaryKey(cartId);
        return res > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Boolean addCart(ItemCart cart) {
        User user = userService.selectByUsername(cart.getUsername());
        Item item = itemSevice.getById(cart.getItemId());
        cart.setUserId(user.getId());
        //0为未购买
        cart.setItemCartStatus(0);
        cart.setItemCatId(item.getCid());
        int res = cartMapper.insert(cart);
        return res > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Boolean updateCart(Integer userId, Integer itemId,Integer quantity) {
        ItemCartExample example = new ItemCartExample();
        example.createCriteria().andUserIdEqualTo(userId).andItemIdEqualTo(itemId);
        List<ItemCart> list = cartMapper.selectByExample(example);
        list.forEach(cart->{
            cart.setQuantity(quantity);
            cartMapper.updateByPrimaryKey(cart);
        });
        return Boolean.TRUE;
    }

    @Override
    public Boolean clean(List<Integer> cartIds) {
        cartIds.forEach(id->{
            cartMapper.deleteByPrimaryKey(id);
        });
        return Boolean.TRUE;
    }
}
