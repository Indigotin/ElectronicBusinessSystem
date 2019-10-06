package com.hlq.course.dao;

import com.hlq.course.pojo.PanelContent;
import com.hlq.course.pojo.PanelContentExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface PanelContentMapper {
    int countByExample(PanelContentExample example);

    int deleteByExample(PanelContentExample example);

    int deleteByPrimaryKey(Integer panelContentId);

    int insert(PanelContent record);

    int insertSelective(PanelContent record);

    List<PanelContent> selectByExample(PanelContentExample example);

    PanelContent selectByPrimaryKey(Integer panelContentId);

    int updateByExampleSelective(@Param("record") PanelContent record, @Param("example") PanelContentExample example);

    int updateByExample(@Param("record") PanelContent record, @Param("example") PanelContentExample example);

    int updateByPrimaryKeySelective(PanelContent record);

    int updateByPrimaryKey(PanelContent record);

    List<Integer> selectItemIdsByPanelId(@Param("panelId") Integer panelId, @Param("limit") Integer itemLimit);
}