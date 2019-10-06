package com.hlq.course.controller;

import com.google.common.collect.Lists;
import com.hlq.course.common.Message;
import com.hlq.course.model.PanelModel;
import com.hlq.course.pojo.Item;
import com.hlq.course.service.ItemSevice;
import com.hlq.course.service.PanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Len on 2019-10-02
 */
@RestController
@RequestMapping("/panel")
public class PanelController {
    @Autowired
    private PanelService panelService;
    @RequestMapping("/view/{panelId}")
    public Message getItems(@PathVariable("id1")Integer panelId){
        PanelModel panelModel = panelService.getPanelById(panelId);
        return Message.success(panelModel);
    }
    @RequestMapping("/cat/{id1}/{id2}/{itemLimit}")
    public Message getItems(@PathVariable("id1")Integer id1,@PathVariable("id2")Integer id2,@PathVariable("itemLimit")Integer itemLimit){
        List<PanelModel> panels = panelService.getPanelByItemCatId(Lists.newArrayList(id1,id2),itemLimit);
        //itemSevice.getByCatIds(Lists.newArrayList(id1,id2),itemLimit);
        return Message.success(panels);
    }

    @RequestMapping("/re/{remark}/{panelLimit}/{itemLimit}")
    public Message getPanelByRemark(@PathVariable("remark")String remark,@PathVariable("panelLimit")Integer panelLimit,@PathVariable("itemLimit")Integer itemLimit) {
        List<PanelModel> panelModels = panelService.getPanelByRemark(remark,panelLimit,itemLimit);
        return Message.success(panelModels);
    }
}
