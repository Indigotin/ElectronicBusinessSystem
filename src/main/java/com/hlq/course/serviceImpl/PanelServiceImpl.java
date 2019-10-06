package com.hlq.course.serviceImpl;

import com.google.common.collect.Lists;
import com.hlq.course.dao.ItemMapper;
import com.hlq.course.dao.PanelContentMapper;
import com.hlq.course.dao.PanelMapper;
import com.hlq.course.model.PanelModel;
import com.hlq.course.pojo.Item;
import com.hlq.course.pojo.Panel;
import com.hlq.course.pojo.PanelExample;
import com.hlq.course.service.PanelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Len on 2019-10-02
 */
@Service
public class PanelServiceImpl implements PanelService {

    @Autowired
    private PanelMapper panelMapper;
    @Autowired
    private PanelContentMapper panelContentMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Override
    public List<PanelModel> getPanelByRemark(String remark, Integer panelLimit, Integer itemLimit) {
        PanelExample example = new PanelExample();
        example.createCriteria().andRemarkEqualTo(remark);
        List<PanelModel> panels = panelMapper.selectByExample(example).stream().
                map(panel -> new ModelMapper().map(panel,PanelModel.class)).
                collect(Collectors.toList());
        panels = getItems(panels, itemLimit);
        return panels;
    }

    private List<PanelModel> getItems(List<PanelModel> panels,Integer itemLimit){
        panels.forEach(panel -> {
            List<Integer> itemIds = panelContentMapper
                    .selectItemIdsByPanelId(panel.getPanelId(), itemLimit);
            if (itemIds.size() != 0) {
                List<Item> items = Lists.newArrayList();
                itemIds.forEach(id->{
                    items.add(itemMapper.selectByPrimaryKey(id));
                });
                panel.setItemList(items);
            }
        });
        return panels;
    }

    @Override
    public List<PanelModel> getPanelByItemCatId(List<Integer> itemCatIds, Integer itemLimit) {

        PanelExample example = new PanelExample();
        example.createCriteria().andItemCatIdIn(itemCatIds);
        List<PanelModel> panels = panelMapper.selectByExample(example).stream().map(panel->
                new ModelMapper().map(panel,PanelModel.class)).collect(Collectors.toList());
        panels = getItems(panels, itemLimit);
        return panels;
    }

    @Override
    public PanelModel getPanelById(Integer id) {
        Panel panel = panelMapper.selectByPrimaryKey(id);
        return getItems(Lists.newArrayList(new ModelMapper().map(panel,PanelModel.class)),20).get(0);
    }

}
