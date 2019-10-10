package com.hlq.course.service;

import com.hlq.course.common.TreeNode;
import com.hlq.course.model.CategoryModel;
import com.hlq.course.pojo.Category;

import java.util.List;

/**
 * Created by Len on 2019-09-26
 */
public interface CategoryService {
    List<Category> getCategoryList();
    List<List<CategoryModel>> getRootCat(Integer catLimit,Integer itemLimit);
    List<Integer> getChildCatIds(Integer categoryId);
    List<CategoryModel> getItemCatWithItems(Integer catLimit, Integer itemLimit);
    List<TreeNode> getCategoryNode();
}
