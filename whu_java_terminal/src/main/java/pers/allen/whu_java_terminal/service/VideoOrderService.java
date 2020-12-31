package pers.allen.whu_java_terminal.service;

import pers.allen.whu_java_terminal.model.entity.VideoOrder;

import java.util.List;

public interface VideoOrderService {
    int save(int userId, int videoId);

    List<VideoOrder> listOrderByUserId(Integer userId);
}
