package com.hlq.course.dao;

import com.hlq.course.pojo.ItemCart;
import com.hlq.course.pojo.ItemCartExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ItemCartMapper {
    int countByExample(ItemCartExample example);

    int deleteByExample(ItemCartExample example);

    int deleteByPrimaryKey(Integer cartId);

    int insert(ItemCart record);

    int insertSelective(ItemCart record);

    List<ItemCart> selectByExample(ItemCartExample example);

    ItemCart selectByPrimaryKey(Integer cartId);

    int updateByExampleSelective(@Param("record") ItemCart record, @Param("example") ItemCartExample example);

    int updateByExample(@Param("record") ItemCart record, @Param("example") ItemCartExample example);

    int updateByPrimaryKeySelective(ItemCart record);

    int updateByPrimaryKey(ItemCart record);
}