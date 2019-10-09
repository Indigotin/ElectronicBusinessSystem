package com.hlq.course.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hlq.course.dao.ItemMapper;
import com.hlq.course.pojo.Item;
import com.hlq.course.pojo.ItemExample;
import com.hlq.course.service.ItemSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Len on 2019-09-29
 */
@Service
public class ItemSeviceImpl implements ItemSevice {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public List<Item> getByCatIds(List<Integer> ids, Integer itemLimit) {
        ItemExample example = new ItemExample();
        List<Item> list = new ArrayList<>();
        ids.forEach(id->{
            example.clear();
            example.createCriteria().andCidEqualTo(id);
            list.addAll(itemMapper.selectByExample(example));
        });
        return list;
    }

    @Override
    public Item getById(Integer id) {
        return itemMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<List<Item>> getByIds(List<Integer> itemIds, Integer page, Integer limit) {
        List<Item> list = Lists.newArrayList();
        itemIds.forEach(id->{
            list.add(itemMapper.selectByPrimaryKey(id));
        });
        return Lists.partition(list,limit);
    }

    @Override
    public PageInfo<Item> getItemsPage(String name, Integer cur, Integer size) {
        Page<Item> page = PageHelper.startPage(cur,size,true);
        ItemExample example = new ItemExample();
        example.createCriteria().andNameLike("%"+name+"%");
        itemMapper.selectByExample(example);
        PageInfo pageInfo =  page.toPageInfo();
        pageInfo.setList(page.getResult());
        return pageInfo;
    }

    @Override
    public Boolean addItem(Item item) {
        itemMapper.insert(item);
        return Boolean.TRUE;
    }
}
