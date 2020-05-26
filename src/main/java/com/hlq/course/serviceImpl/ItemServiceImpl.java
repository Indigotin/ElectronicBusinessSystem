package com.hlq.course.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hlq.course.common.SendMessage;
import com.hlq.course.dao.ItemMapper;
import com.hlq.course.model.ItemCartModel;
import com.hlq.course.pojo.Item;
import com.hlq.course.pojo.ItemExample;
import com.hlq.course.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Len on 2019-09-29
 */
@Service
public class ItemServiceImpl implements ItemService {

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
    public List<Item> getRecommeds(List<ItemCartModel> itemCarts, Integer limit) {
        List<Item> list = new ArrayList<>();
        SendMessage smg = new SendMessage();
        String ids = smg.sendMessageCart(itemCarts);
        //提取itemid
        String[] strs = ids.substring(1,ids.length()-1).split("[,]");
        /*for(int i=0;i<strs.length;i++)
            System.out.println(strs[i]);*/
        if(strs != null)
        //封装item 限制limit
            for(int i=0;i<strs.length&&i<limit;i++){
                int id = Integer.valueOf(strs[i].trim());
                list.add(itemMapper.selectByPrimaryKey(id));
            }
        //strs=null
        itemCarts.forEach(temp->{
            Integer cid = itemMapper.selectByPrimaryKey(temp.getItemId()).getCid();
            ItemExample example = new ItemExample();
            example.createCriteria().andCidEqualTo(cid);
            List<Item> itemTempList = itemMapper.selectByExample(example);
            for(int i=0;i<2&&list.size()<limit&&i<itemTempList.size();i++){
                Item item = itemTempList.get(i);
                if(item.getId() != temp.getItemId()){
                    list.add(item);
                }else{
                    continue;
                }
            }
        });
        //返回
        return list;
    }

    /*public List<Item> getByIds(List<Integer> itemIds, Integer limit) {
        List<Item> list = Lists.newArrayList();
        itemIds.forEach(id->{
            Integer cid = itemMapper.selectByPrimaryKey(id).getCid();
            ItemExample example = new ItemExample();
            example.createCriteria().andCidEqualTo(cid);
            List<Item> itemTempList = itemMapper.selectByExample(example);
            for(int i=0;i<5&&list.size()<limit;i++){
                Item item = itemTempList.get(i);
                if(item.getId() != id){
                    list.add(item);
                }else{
                    continue;
                }
            }
        });
        return list;
    }*/

    @Override
    public PageInfo<Item> getItemsPage(String name, Integer cur, Integer size) {
        Page<Item> page = PageHelper.startPage(cur,size,true);
        ItemExample example = new ItemExample();
        if(name !=null && !name.equals("")){
            example.createCriteria().andNameLike("%"+name+"%");
        }
        itemMapper.selectByExample(example);
        PageInfo pageInfo =  page.toPageInfo();
        pageInfo.setList(page.getResult());
        return pageInfo;
    }

    @Override
    public Boolean addItem(Item item) {
        itemMapper.insert(item);
        SendMessage smg = new SendMessage();
        String result = smg.sendMessageAddItem(JSON.toJSONString(item));
        System.out.println("result:"+result);
        return Boolean.TRUE;
    }

    @Override
    public Boolean editItem(Item item) {
        return itemMapper.updateByPrimaryKeySelective(item) == 1;
    }

    @Override
    public PageInfo<Item> getItemList(Integer pageNumber,Integer pageSize) {
        Page<Item> page = PageHelper.startPage(pageNumber,pageSize,true);
        itemMapper.selectByExample(new ItemExample());
        PageInfo pageInfo =  page.toPageInfo();
        pageInfo.setList(page.getResult());
        return pageInfo;
    }

    @Override
    public PageInfo<Item> getSearchList(String itemName, String id, Integer pageNumber, Integer pageSize) {
        Page<Item> page = PageHelper.startPage(pageNumber,pageSize,true);
        ItemExample example = new ItemExample();
        Boolean nameFlag = itemName!=null && !itemName.equals("") && !itemName.equals("undefined");
        Boolean idFlag = id != null && !id.equals("") && !id.equals("undefined");
        if(nameFlag){
            if(idFlag){
                example.createCriteria().andNameLike("%"+itemName+"%").andIdEqualTo(Integer.valueOf(id));
            }else{
                example.createCriteria().andNameLike("%"+itemName+"%");
            }
        }
        if(idFlag){
            example.createCriteria().andIdEqualTo(Integer.valueOf(id));
        }
        itemMapper.selectByExample(example);
        PageInfo pageInfo =  page.toPageInfo();
        pageInfo.setList(page.getResult());
        return pageInfo;
    }
}
