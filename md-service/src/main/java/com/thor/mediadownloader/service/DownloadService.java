package com.thor.mediadownloader.service;

import com.github.kiulian.downloader.YoutubeDownloader;
import com.github.kiulian.downloader.downloader.request.RequestVideoInfo;
import com.github.kiulian.downloader.downloader.request.RequestVideoStreamDownload;
import com.github.kiulian.downloader.downloader.response.Response;
import com.github.kiulian.downloader.model.videos.VideoDetails;
import com.github.kiulian.downloader.model.videos.VideoInfo;
import com.github.kiulian.downloader.model.videos.formats.AudioFormat;
import com.github.kiulian.downloader.model.videos.formats.Format;
import com.github.kiulian.downloader.model.videos.formats.VideoFormat;
import com.github.kiulian.downloader.model.videos.formats.VideoWithAudioFormat;
import com.thor.mediadownloader.model.VideoInfoDetail;
import com.thor.mediadownloader.utils.YoutubeDownloaderUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.List;

@Service
public class DownloadService {

    private static final Logger logger = LoggerFactory.getLogger(DownloadService.class);

    /**
     * Doc -> https://github.com/sealedtx/java-youtube-downloader
     * @param url
     * @param formatStr
     * @return
     */
    public InputStream download(String url, String formatStr) {
        YoutubeDownloader downloader = YoutubeDownloaderUtil.instance();
        String youtubeId = getYoutubeId(url);
        RequestVideoInfo request = new RequestVideoInfo(youtubeId);
        Response<VideoInfo> response = downloader.getVideoInfo(request);
        VideoInfo video = response.data();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        RequestVideoStreamDownload downloadRequest = new RequestVideoStreamDownload(getFormatFromString(formatStr, video), os);
        Response<Void> downloadResponse = downloader.downloadVideoStream(downloadRequest);
        byte[] bytes = os.toByteArray();

        return new ByteArrayInputStream(bytes);
    }

    public VideoInfoDetail getVideoInfo(String url) {
        YoutubeDownloader downloader = YoutubeDownloaderUtil.instance();
        String youtubeId = getYoutubeId(url);
        RequestVideoInfo request = new RequestVideoInfo(youtubeId);
        Response<VideoInfo> response = downloader.getVideoInfo(request);

        return new VideoInfoDetail(response.data());
    }

    private String getYoutubeId(String url) {
        String[] str;
        String id = "";

        if (url.contains("youtu.be")) {
            url = url.replace("https://", "");
            str = url.split("/");
            id = str[str.length - 1];
        } else if (url.contains("www.youtube.com")) {
            str = url.split("watch\\?v=");
            id = str[str.length - 1];
        }

        if (id.contains("&list="))
            id = id.split("&list=")[0];
        if (id.contains("?list="))
            id = id.split("\\?list=")[0];
        if (id.contains("?t="))
            id = id.split("\\?t=")[0];

        return id;
    }

    /**
     * Info - pode ser obtido a melhor qualidade de cada elemento audio/video com o metodo bestXYZ
     * @param video
     * @param videoWithAudioFormats
     * @param videoFormats
     * @param audioFormats
     */
    private void printVideoContent(VideoInfo video, List<VideoWithAudioFormat> videoWithAudioFormats, List<VideoFormat> videoFormats, List<AudioFormat> audioFormats) {
        logger.info(video.details().title());
        logger.info(video.details().videoId());
        logger.info(video.details().author());
        logger.info("Average rating: {}", video.details().averageRating());
        logger.info("View count: {}", video.details().viewCount());
        System.out.println("=== Video With Audio ===");
        videoWithAudioFormats.forEach(it -> {
            System.out.println(it.audioQuality() + ", " + it.videoQuality() + " : " + it.url());
        });
        System.out.println("=== Only Video ===");
        videoFormats.forEach(it -> {
            System.out.println(it.videoQuality() + " : " + it.url());
        });
        System.out.println("=== Only Audio ===");
        audioFormats.forEach(it -> {
            System.out.println(it.audioQuality() + " : " + it.url());
        });
    }

    private Format getFormatFromString(String formatStr, VideoInfo video) {
        switch(formatStr) {
            case "audio":
                return video.bestAudioFormat();
            case "video":
                return video.bestVideoWithAudioFormat();
            case "video-no-sound":
                return video.bestVideoFormat();
        }
    }

}
