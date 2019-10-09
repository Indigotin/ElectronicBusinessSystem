package com.hlq.course.service;

import com.github.pagehelper.PageInfo;
import com.hlq.course.pojo.Item;

import java.util.List;

/**
 * Created by Len on 2019-09-29
 */
public interface ItemSevice {
    List<Item> getByCatIds(List<Integer> ids, Integer itemLimit);
    Item getById(Integer id);
    List<List<Item>> getByIds(List<Integer> itemIds,Integer page,Integer limit);
    PageInfo<Item> getItemsPage(String name,Integer cur,Integer size);
    Boolean addItem(Item item);
}
