package com.hlq.course.serviceImpl;

import com.google.common.collect.Lists;
import com.hlq.course.common.TreeNode;
import com.hlq.course.dao.CategoryMapper;
import com.hlq.course.model.CategoryModel;
import com.hlq.course.pojo.Category;
import com.hlq.course.pojo.CategoryExample;
import com.hlq.course.pojo.Item;
import com.hlq.course.service.CategoryService;
import com.hlq.course.service.ItemSevice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Len on 2019-09-25
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ItemSevice itemSevice;

    public List<Category> getCategoryList(){
        List<Category> list = categoryMapper.selectByExample(new CategoryExample());
        return list;
    }

    @Override
    public List<List<CategoryModel>> getRootCat(Integer catLimit,Integer itemLimit) {
        CategoryExample example = new CategoryExample();
        example.createCriteria().andParentIdEqualTo(0);
        List<CategoryModel> categoryModelList = categoryMapper.selectByExample(example).stream().
                map(category -> new ModelMapper().map(category,CategoryModel.class)).
                collect(Collectors.toList());
        categoryModelList.forEach(categoryModel -> {
            List<Integer> ids = getChildCatIds(categoryModel.getId());
            ids.add(categoryModel.getId());
            List<Item> itemData = itemSevice.getByCatIds(ids, itemLimit);
            categoryModel.setItems(itemData);
        });
        return Lists.partition(categoryModelList,2);
    }

    @Override
    public List<Integer> getChildCatIds(Integer categoryId) {
        CategoryExample example = new CategoryExample();
        example.createCriteria().andParentIdEqualTo(categoryId);
        List<Category> categoryList = categoryMapper.selectByExample(example);
        List<Integer> ids = new ArrayList<>();
        categoryList.forEach(category -> {
            ids.add(category.getId());
        });
        return ids;
    }

    @Override
    public List<CategoryModel> getItemCatWithItems(Integer catLimit, Integer itemLimit) {
        CategoryExample example = new CategoryExample();
        example.createCriteria().andRemarkEqualTo("nav");
        List<CategoryModel> categoryModelList = categoryMapper.selectByExample(example).stream().
                map(category -> new ModelMapper().map(category,CategoryModel.class)).
                collect(Collectors.toList());
        categoryModelList.forEach(categoryModel -> {
            List<Integer> ids = getChildCatIds(categoryModel.getId());
            ids.add(categoryModel.getId());
            List<Item> itemData = itemSevice.getByCatIds(ids, itemLimit);
            categoryModel.setItems(itemData);
        });
        return categoryModelList;
    }

    @Override
    public List<TreeNode> getCategoryNode() {
        List<TreeNode> nodes;// = Lists.newArrayList();
        nodes = getCategoryNode(0);
        return nodes;
    }

    public List<TreeNode> getCategoryNode(Integer parentId) {
        //根据parentId查询子节点列表
        CategoryExample example = new CategoryExample();
        //设置查询条件
        example.createCriteria().andParentIdEqualTo(parentId);
        //执行查询
        List<Category> list = categoryMapper.selectByExample(example);
        List<TreeNode> resultList = new ArrayList<>();
        for(Category category:list) {
            TreeNode node = new TreeNode();
            node.setTitle(category.getName());
            node.setKey(category.getId());
            node.setValue(category.getId());
            node.setLeaf(category.getIsParent()?Boolean.FALSE:Boolean.TRUE);
            if(!node.getLeaf()){
                node.setChildren(getCategoryNode(node.getKey()));
            }
            resultList.add(node);
        }
        return resultList;
    }

}
