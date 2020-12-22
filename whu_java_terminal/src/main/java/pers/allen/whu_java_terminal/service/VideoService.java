package pers.allen.whu_java_terminal.service;

import pers.allen.whu_java_terminal.model.Video;
import pers.allen.whu_java_terminal.model.VideoBanner;

import java.util.List;

public interface VideoService {

    List<Video> listVideo();

    List<VideoBanner> listBanner();

    Video findDetailById(int videoId);
}
