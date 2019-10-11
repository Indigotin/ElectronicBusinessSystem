package com.hlq.course.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hlq.course.common.Message;
import com.hlq.course.pojo.Item;
import com.hlq.course.service.ItemSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.awt.*;
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

    @RequestMapping("/es/recommend/{limit}")
    public Message getRecommed(@RequestBody List<Integer> itemIds, @PathVariable("limit")Integer limit){
        return Message.success(itemSevice.getByIds(itemIds,limit));
    }

    @RequestMapping("/item/search/{searchValue}/{page}/{size}")
    public Message search(@PathVariable("searchValue")String searchValue,@PathVariable("page")Integer page,@PathVariable("size")Integer size){
        PageInfo<Item> list = itemSevice.getItemsPage(searchValue,page,size);
        return Message.success(list);
    }

    @RequestMapping("/item/add")
    public Message addItem(@Valid @ModelAttribute Item item){
        if(itemSevice.addItem(item)){
            return Message.success();
        }
        return Message.failed();
    }

    @RequestMapping("/item/edit")
    public Message editItem(@Valid @ModelAttribute Item item){
        //item.setId(id);
        if(itemSevice.editItem(item)){
            return Message.success();
        }
        return Message.failed();
    }

    @RequestMapping("/item/list/{pageNumber}/{pageSize}")
    public Message getItemList(@PathVariable("pageNumber") Integer pageNumber,@PathVariable("pageSize")Integer pageSize){
        PageInfo<Item> items = itemSevice.getItemList(pageNumber,pageSize);
        return Message.success(items);
    }

}
