package com.hlq.course.dao;

import com.hlq.course.pojo.Gifts;
import com.hlq.course.pojo.GiftsExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface GiftsMapper {
    int countByExample(GiftsExample example);

    int deleteByExample(GiftsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Gifts record);

    int insertSelective(Gifts record);

    List<Gifts> selectByExample(GiftsExample example);

    Gifts selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Gifts record, @Param("example") GiftsExample example);

    int updateByExample(@Param("record") Gifts record, @Param("example") GiftsExample example);

    int updateByPrimaryKeySelective(Gifts record);

    int updateByPrimaryKey(Gifts record);
}