package pers.allen.whu_java_terminal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.allen.whu_java_terminal.exception.MYException;
import pers.allen.whu_java_terminal.mapper.*;
import pers.allen.whu_java_terminal.model.entity.Episode;
import pers.allen.whu_java_terminal.model.entity.PlayRecord;
import pers.allen.whu_java_terminal.model.entity.Video;
import pers.allen.whu_java_terminal.model.entity.VideoOrder;
import pers.allen.whu_java_terminal.service.VideoOrderService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class VideoOrderServiceImpl implements VideoOrderService {

    @Autowired
    public VideoOrderMapper videoOrderMapper;
    @Autowired
    public VideoMapper videoMapper;
    @Autowired
    public UserMapper userMapper;
    @Autowired
    public EpisodeMapper episodeMapper;
    @Autowired
    public PlayRecordMapper playRecordMapper;

    @Override
    public int save(int userId, int videoId) {
        //判断是否已经购买
        VideoOrder videoOrder = videoOrderMapper.findByUserIdAndVideoIdAndState(userId,videoId,1); //state==1：已经支付过了

        if(videoOrder!=null){return  0;}

        Video video = videoMapper.findById(videoId);

        VideoOrder newVideoOrder = new VideoOrder();
        newVideoOrder.setCreateTime(new Date());
        newVideoOrder.setOutTradeNo(UUID.randomUUID().toString());  //随机UUID作为订单号
        newVideoOrder.setState(1);
        newVideoOrder.setTotalFee(video.getPrice());
        newVideoOrder.setUserId(userId);

        newVideoOrder.setVideoId(videoId);
        newVideoOrder.setVideoImg(video.getCoverImg());
        newVideoOrder.setVideoTitle(video.getTitle());

        int rows = videoOrderMapper.saveOrder(newVideoOrder); //插入成功返回1（插入1行） 插入不成功返回0

        //生成播放记录
        if(rows == 1){
            Episode episode = episodeMapper.findFirstEpisodeByVideoId(videoId);
            if(episode == null){
                throw  new MYException(-1,"视频没有集信息，请运营人员检查");
            }
            PlayRecord playRecord = new PlayRecord();
            playRecord.setCreateTime(new Date());
            playRecord.setEpisodeId(episode.getId());
            playRecord.setCurrentNum(episode.getNum());
            playRecord.setUserId(userId);
            playRecord.setVideoId(videoId);
            playRecordMapper.saveRecord(playRecord);
        }

        return rows;
    }

    @Override
    public List<VideoOrder> listOrderByUserId(Integer userId) {

        return videoOrderMapper.listOrderByUserId(userId);
    }
}
