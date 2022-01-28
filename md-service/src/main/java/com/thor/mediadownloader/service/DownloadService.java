package com.thor.mediadownloader.service;

import com.github.kiulian.downloader.YoutubeDownloader;
import com.github.kiulian.downloader.downloader.request.RequestVideoFileDownload;
import com.github.kiulian.downloader.downloader.request.RequestVideoInfo;
import com.github.kiulian.downloader.downloader.response.Response;
import com.github.kiulian.downloader.model.videos.VideoDetails;
import com.github.kiulian.downloader.model.videos.VideoInfo;
import com.github.kiulian.downloader.model.videos.formats.AudioFormat;
import com.github.kiulian.downloader.model.videos.formats.Format;
import com.github.kiulian.downloader.model.videos.formats.VideoFormat;
import com.github.kiulian.downloader.model.videos.formats.VideoWithAudioFormat;
import com.thor.mediadownloader.utils.YoutubeDownloaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
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
    public File download(String url, String formatStr) {
        YoutubeDownloader downloader = YoutubeDownloaderUtil.instance();
        String youtubeId = getYoutubeId(url);
        RequestVideoInfo request = new RequestVideoInfo(youtubeId);
        Response<VideoInfo> response = downloader.getVideoInfo(request);
        VideoInfo video = response.data();
        VideoDetails details = video.details();
        File outputFile = new File("youtube_files");

        Format format = null;
        String titulo = null;
        switch(formatStr) {
            case "audio":
                titulo = "Audio - " + details.title();
                format = video.bestAudioFormat();
                break;
            case "video":
                titulo = "Original - " + details.title();
                format = video.bestVideoWithAudioFormat();
                break;
            case "video-no-sound":
                titulo = "Video - " + details.title();
                format = video.bestVideoFormat();
                break;
        }

        RequestVideoFileDownload downloadRequest = new RequestVideoFileDownload(format)
                .saveTo(outputFile)
                .renameTo(titulo)
                .overwriteIfExists(true);
        logger.info("{} downloaded", details.title());

        return downloader.downloadVideoFile(downloadRequest).data();
    }

    private String getYoutubeId(String url) {
        String[] str = url.split("watch" + "\\?" + "v=");
        return str[str.length - 1];
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

}
