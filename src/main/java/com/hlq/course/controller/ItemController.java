package com.hlq.course.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hlq.course.common.Message;
import com.hlq.course.pojo.Item;
import com.hlq.course.service.ItemSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Len on 2019-09-30
 */
@RestController
public class ItemController {

    @Autowired
    private ItemSevice itemSevice;
    @RequestMapping("/item/{id}")
    public Message getItem(@PathVariable("id")Integer id){
        Item item = itemSevice.getById(id);
        if(item != null){
            return Message.success(item);
        }
        return Message.failed();
    }

    @RequestMapping("/es/recommend/{page}/{limit}")
    public Message getRecommed(@RequestBody List<Integer> itemIds, @PathVariable("page")Integer page, @PathVariable("limit")Integer limit){
        return Message.success(itemSevice.getByIds(itemIds,page,limit));
    }

    @RequestMapping("/item/search/{searchValue}")
    public Message search(@PathVariable("searchValue")String searchValue,Integer page){
        PageInfo<Item> list = itemSevice.getItemsPage(searchValue,1,30);
        return Message.success(list);
    }

    @RequestMapping("/item/add")
    public Message addItem(@RequestBody Item item){
        if(itemSevice.addItem(item)){
            return Message.success();
        }
        return Message.failed();
    }

}
