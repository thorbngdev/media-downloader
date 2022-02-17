package com.thor.mediadownloader.model;

import com.github.kiulian.downloader.model.videos.VideoInfo;
import com.thor.mediadownloader.utils.YoutubeDownloaderUtil;

import java.util.List;

public class VideoInfoDetail {

    private String videoId;
    private String title;
    private String author;
    private List<String> thumbnails;
    private String description;
    private long viewCount;
    private int averageRating;
    private List<String> keywords;
    private String duration;
    private List<String> formatTypes;

    public VideoInfoDetail() {
    }

    public VideoInfoDetail(VideoInfo videoInfo) {
        videoId = videoInfo.details().videoId();
        title = videoInfo.details().title();
        author = videoInfo.details().author();
        thumbnails = videoInfo.details().thumbnails();
        description = videoInfo.details().description();
        viewCount = videoInfo.details().viewCount();
        averageRating = videoInfo.details().averageRating();
        keywords = videoInfo.details().keywords();
        duration = YoutubeDownloaderUtil.getVideoDuration(videoInfo.details().lengthSeconds());
        formatTypes = YoutubeDownloaderUtil.getVideoFormatTypes(videoInfo.formats());
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(List<String> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getViewCount() {
        return viewCount;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    public int getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(int averageRating) {
        this.averageRating = averageRating;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<String> getFormatTypes() {
        return formatTypes;
    }

    public void setFormatTypes(List<String> formatTypes) {
        this.formatTypes = formatTypes;
    }
}
