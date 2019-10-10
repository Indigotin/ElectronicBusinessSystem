package com.hlq.course.model;

import com.hlq.course.pojo.OrderItem;

/**
 * Created by Len on 2019-10-10
 */
public class OrderItemModel extends OrderItem {
    private String itemImage;
    private String itemName;

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
