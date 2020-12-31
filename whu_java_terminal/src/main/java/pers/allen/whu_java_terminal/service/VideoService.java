package pers.allen.whu_java_terminal.service;

import pers.allen.whu_java_terminal.model.entity.Video;
import pers.allen.whu_java_terminal.model.entity.VideoBanner;

import java.util.List;

public interface VideoService {

    List<Video> listVideo();

    List<VideoBanner> listBanner();

    Video findDetailById(int videoId);
}
