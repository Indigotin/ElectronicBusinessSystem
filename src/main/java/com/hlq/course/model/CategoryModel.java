package com.hlq.course.model;

import com.hlq.course.pojo.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Len on 2019-09-29
 */
public class CategoryModel implements Serializable {

    private static final long serialVersionUID = 3026371933152829463L;

    private Integer id;

    private Boolean isParent;

    private String name;

    private Long parentId;

    private String remark;

    private Integer sortOrder;

    private List<CategoryModel> childCats;

    private List<Item> items;

    public CategoryModel() {
        childCats = new ArrayList();
        items = new ArrayList();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<CategoryModel> getChildCats() {
        return childCats;
    }

    public void setChildCats(List<CategoryModel> childCats) {
        this.childCats = childCats;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "CategoryModel{" + "id=" + id + ", icon='"
                + ", isParent=" + isParent + ", name='" + name + '\'' + ", parentId="
                + parentId + ", remark='" + remark + '\'' + ", sortOrder=" + sortOrder
                + ", childCats=" + childCats + ", items=" + items + '}';
    }
}
