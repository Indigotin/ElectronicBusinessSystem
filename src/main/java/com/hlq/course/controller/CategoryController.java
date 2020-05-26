package com.hlq.course.controller;

import com.hlq.course.common.Message;
import com.hlq.course.common.TreeNode;
import com.hlq.course.model.CategoryModel;
import com.hlq.course.service.CategoryService;
import com.hlq.course.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Len on 2019-09-25
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ItemService productSevice;

    @RequestMapping(path = "/ro/{catLimit}/{itemLimit}")
    public Message rootCatWithProducts(Integer catLimit,Integer itemLimit) {
        try {
            List<List<CategoryModel>> data = categoryService.getRootCat(catLimit,itemLimit);
			return Message.success(data);
        }
		catch (Exception e) {
            e.printStackTrace();
        }
        return Message.failed();
    }

    @RequestMapping(path = "/nav/{catLimit}/{itemLimit}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Message navCatWithItems(Integer catLimit, Integer itemLimit) {
        return Message.success(categoryService.getItemCatWithItems(catLimit, itemLimit));
    }

    @RequestMapping("/category")
    public Message getCategory(){
        return Message.success(categoryService.getCategoryList());
    }

    @RequestMapping("/get")
    public Message getCategoryNode(){
        List<TreeNode> nodes = categoryService.getCategoryNode();
        return Message.success(nodes);
    }
}
