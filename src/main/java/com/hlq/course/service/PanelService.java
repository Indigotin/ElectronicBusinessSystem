package com.hlq.course.service;

import com.hlq.course.model.PanelModel;

import java.util.List;

/**
 * Created by Len on 2019-10-02
 */
public interface PanelService {
    List<PanelModel> getPanelByRemark(String remark,Integer panelLimit,Integer itemLimit);
    List<PanelModel> getPanelByItemCatId(List<Integer> itemCatIds, Integer itemLimit);
    PanelModel getPanelById(Integer id);
}
