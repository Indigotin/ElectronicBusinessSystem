package com.hlq.course.controller;

import com.hlq.course.common.Message;
import com.hlq.course.pojo.Gifts;
import com.hlq.course.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Len on 2019-09-25
 */
@RestController
public class GiftController {
    @Autowired
    private GiftService giftService;

    @RequestMapping("/addGift")
    public Message addGift(Gifts gift){
        return Message.success(giftService.addGitf(gift));
    }


}
