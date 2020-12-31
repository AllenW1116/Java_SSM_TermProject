package pers.allen.whu_java_terminal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.allen.whu_java_terminal.model.entity.Video;
import pers.allen.whu_java_terminal.model.entity.VideoBanner;
import pers.allen.whu_java_terminal.service.VideoService;
import pers.allen.whu_java_terminal.utils.JsonData;

import java.util.List;

@RestController
@RequestMapping("api/v1/pub/video")
public class VideoController {
    @Autowired
    private VideoService videoService;


    @RequestMapping("test")
    public String test(){
        //System.out.println("热部署！");
        System.out.println("controller中的方法执行了 热部署 部尼玛！哦哦 原来还要ctrl+f9……");
        return "controller中的方法执行了，热部署当场更改！";
    }

   /**
     * 轮播图列表
     * @return
     */
    @GetMapping("list_banner")
    public JsonData indexBanner(){

        List<VideoBanner> bannerList =  videoService.listBanner();

        return JsonData.buildSuccess(bannerList);

    }


    /**
     * 视频列表
     * @return
     */
    @RequestMapping("list")
    public JsonData listVideo(){

        List<Video> videoList = videoService.listVideo();
        return JsonData.buildSuccess(videoList);
    }

    /**
     * 查询视频详情，包含章，集信息
     * @param videoId
     * @return
     */
    @GetMapping("find_detail_by_id")
    public JsonData findDetailById(@RequestParam(value = "video_id",required = true)int videoId){//参数必传

        Video video = videoService.findDetailById(videoId);

        return JsonData.buildSuccess(video);

    }
}
