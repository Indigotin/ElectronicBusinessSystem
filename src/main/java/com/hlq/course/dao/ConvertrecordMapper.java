package com.hlq.course.dao;

import com.hlq.course.pojo.Convertrecord;
import com.hlq.course.pojo.ConvertrecordExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ConvertrecordMapper {
    int countByExample(ConvertrecordExample example);

    int deleteByExample(ConvertrecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Convertrecord record);

    int insertSelective(Convertrecord record);

    List<Convertrecord> selectByExample(ConvertrecordExample example);

    Convertrecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Convertrecord record, @Param("example") ConvertrecordExample example);

    int updateByExample(@Param("record") Convertrecord record, @Param("example") ConvertrecordExample example);

    int updateByPrimaryKeySelective(Convertrecord record);

    int updateByPrimaryKey(Convertrecord record);
}