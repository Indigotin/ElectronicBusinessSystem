package com.hlq.course.serviceImpl;

import com.google.common.collect.Lists;
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

    /*@Override
    public List<EasyUITreeNode> getContentCategoryList(long parentId) {
        //根据parentId查询子节点列表
        TbContentCategoryExample example = new TbContentCategoryExample();
        //设置查询条件
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        List<EasyUITreeNode> resultList = new ArrayList<>();

        for(TbContentCategory tbContentCategory:list) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbContentCategory.getId());
            node.setText(tbContentCategory.getName());
            node.setState(tbContentCategory.getIsParent()?"closed":"open");
            resultList.add(node);
        }
        return resultList;
    }*/

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

}
