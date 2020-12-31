package pers.allen.whu_java_terminal.mapper;

import org.apache.ibatis.annotations.Param;
import pers.allen.whu_java_terminal.model.entity.Episode;

public interface EpisodeMapper {
    Episode findFirstEpisodeByVideoId(@Param("video_id") int videoId);
}
