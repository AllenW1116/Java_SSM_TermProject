package pers.allen.whu_java_terminal.mapper;

import org.apache.ibatis.annotations.Param;
import pers.allen.whu_java_terminal.model.entity.VideoOrder;

import java.util.List;

public interface VideoOrderMapper {
    /**
     * 查询用户是否购买过此商品
     * @param userId
     * @param vidoeId
     * @param state
     * @return
     */
    VideoOrder findByUserIdAndVideoIdAndState(@Param("user_id") int userId, @Param("video_id") int videoId, @Param("state") int state);


    /**
     * 下单
     * @return
     */
    int saveOrder(VideoOrder videoOrder);


    /**
     * 视频列表
     * @param userId
     * @return
     */
    List<VideoOrder> listOrderByUserId(@Param("user_id") Integer userId);
}
