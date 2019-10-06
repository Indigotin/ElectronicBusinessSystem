package com.hlq.course.dao;

import com.hlq.course.pojo.Panel;
import com.hlq.course.pojo.PanelExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface PanelMapper {
    int countByExample(PanelExample example);

    int deleteByExample(PanelExample example);

    int deleteByPrimaryKey(Integer panelId);

    int insert(Panel record);

    int insertSelective(Panel record);

    List<Panel> selectByExample(PanelExample example);

    Panel selectByPrimaryKey(Integer panelId);

    int updateByExampleSelective(@Param("record") Panel record, @Param("example") PanelExample example);

    int updateByExample(@Param("record") Panel record, @Param("example") PanelExample example);

    int updateByPrimaryKeySelective(Panel record);

    int updateByPrimaryKey(Panel record);
}