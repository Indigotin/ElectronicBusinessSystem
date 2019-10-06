package com.hlq.course.service;

import com.hlq.course.pojo.Gifts;

import java.util.List;

/**
 * Created by Len on 2019-09-26
 */
public interface GiftService {
    Boolean addGitf(Gifts gift);
    Boolean deleteGift(int id);
    List<Gifts> getGiftList();
}
