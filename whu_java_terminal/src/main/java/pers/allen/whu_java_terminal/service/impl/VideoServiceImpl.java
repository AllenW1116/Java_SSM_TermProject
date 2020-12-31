package pers.allen.whu_java_terminal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.allen.whu_java_terminal.config.CacheKeyManager;
import pers.allen.whu_java_terminal.mapper.VideoMapper;
import pers.allen.whu_java_terminal.model.entity.Video;
import pers.allen.whu_java_terminal.model.entity.VideoBanner;
import pers.allen.whu_java_terminal.service.VideoService;
import pers.allen.whu_java_terminal.utils.BaseCache;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private BaseCache baseCache;

    @Override
    public List<Video> listVideo() {


        try{

            Object cacheObj =  baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_VIDEO_LIST,()->{ //先从内存里面找，找不到就从数据库里查出来放入内存

                List<Video> videoList = videoMapper.listVideo();

                System.out.println("从数据库里面找视频列表");  //十分钟内再次请求，不会打印该字符串。因为直接从内存找到了，就不用从数据库里找！

                return videoList;

            });

            if(cacheObj instanceof List){  //防止误拿到其它内存，强转一下
                List<Video> videoList = (List<Video>)cacheObj;
                return videoList;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<VideoBanner> listBanner() {

        try{

            Object cacheObj =  baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_BANNER_KEY, ()->{

                List<VideoBanner> bannerList =  videoMapper.listVideoBanner();

                System.out.println("从数据库里面找轮播图列表");  //十分钟内再次请求，不会打印该字符串。因为直接从内存找到了，就不用从数据库里找！

                return bannerList;

            });

            if(cacheObj instanceof List){
                List<VideoBanner> bannerList = (List<VideoBanner>)cacheObj;
                return bannerList;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Video findDetailById(int videoId) {

        //单独构建一个缓存key，每个视频的key是不一样的
        String videoCacheKey = String.format(CacheKeyManager.VIDEO_DETAIL,videoId);

        try{

            Object cacheObject = baseCache.getOneHourCache().get( videoCacheKey, ()->{

                // 需要使用mybaits关联复杂查询
                Video video = videoMapper.findDetailById(videoId);

                return video;

            });

            if(cacheObject instanceof Video){

                Video video = (Video)cacheObject;
                return video;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
