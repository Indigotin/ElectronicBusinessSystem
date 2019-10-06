package com.hlq.course.model;

import com.hlq.course.pojo.Item;
import com.hlq.course.pojo.Panel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Len on 2019-10-02
 */
public class PanelModel extends Panel implements Serializable {
    private List<Item> itemList;

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
