package com.hlq.course.dao;

import com.hlq.course.pojo.Depositrecord;
import com.hlq.course.pojo.DepositrecordExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface DepositrecordMapper {
    int countByExample(DepositrecordExample example);

    int deleteByExample(DepositrecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Depositrecord record);

    int insertSelective(Depositrecord record);

    List<Depositrecord> selectByExample(DepositrecordExample example);

    Depositrecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Depositrecord record, @Param("example") DepositrecordExample example);

    int updateByExample(@Param("record") Depositrecord record, @Param("example") DepositrecordExample example);

    int updateByPrimaryKeySelective(Depositrecord record);

    int updateByPrimaryKey(Depositrecord record);
}