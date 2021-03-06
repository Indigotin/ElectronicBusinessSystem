package com.hlq.course.controller;

import com.github.pagehelper.PageInfo;
import com.hlq.course.common.Message;
import com.hlq.course.model.ItemCartModel;
import com.hlq.course.pojo.Item;
import com.hlq.course.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Len on 2019-09-30
 */
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;
    @RequestMapping("/item/{id}")
    public Message getItem(@PathVariable("id")Integer id){
        Item item = itemService.getById(id);
        if(item != null){
            return Message.success(item);
        }
        return Message.failed();
    }

    /*@RequestMapping("/es/recommend/{limit}")
    public Message getRecommed(@RequestBody List<Integer> itemIds, @PathVariable("limit")Integer limit){
        return Message.success(itemService.getByIds(itemIds,limit));
    }*/

    /*@RequestMapping("/es/recommend/{limit}/{curUser}")
    public Message getRecommed(@RequestBody List<Integer> itemIds,@PathVariable String curUser,
                               @PathVariable("limit")Integer limit){
        System.out.println(itemIds.toString());
        System.out.println(limit);
        System.out.println(curUser);
        return Message.success(itemService.getByIds(itemIds, limit));
    }*/

    @RequestMapping("/es/recommend/{limit}")
    public Message getRecommed(@RequestBody List<ItemCartModel> cartsData, @PathVariable("limit")Integer limit){
        return Message.success(itemService.getRecommeds(cartsData,  limit));
    }

    @RequestMapping("/item/search/{searchValue}/{page}/{size}")
    public Message search(@PathVariable("searchValue")String searchValue,@PathVariable("page")Integer page,@PathVariable("size")Integer size){
        PageInfo<Item> list = itemService.getItemsPage(searchValue,page,size);
        return Message.success(list);
    }

    @RequestMapping("/item/add")
    public Message addItem(@Valid @ModelAttribute Item item){
        if(itemService.addItem(item)){
            return Message.success();
        }
        return Message.failed();
    }

    @RequestMapping("/item/edit")
    public Message editItem(@Valid @ModelAttribute Item item){
        //item.setId(id);
        if(itemService.editItem(item)){
            return Message.success();
        }
        return Message.failed();
    }

    @RequestMapping("/item/list/{pageNumber}/{pageSize}")
    public Message getItemList(@PathVariable("pageNumber") Integer pageNumber,@PathVariable("pageSize")Integer pageSize){
        PageInfo<Item> items = itemService.getItemList(pageNumber,pageSize);
        return Message.success(items);
    }

    @RequestMapping("/item/search/{name}/{goodCode}/{pageNumber}/{pageSize}")
    public Message getSearchList(@PathVariable("name")String name,@PathVariable("goodCode")String goodCode,@PathVariable("pageNumber") Integer pageNumber,@PathVariable("pageSize")Integer pageSize){
        PageInfo<Item> items = itemService.getSearchList(name,goodCode,pageNumber,pageSize);
        return Message.success(items);
    }

}
