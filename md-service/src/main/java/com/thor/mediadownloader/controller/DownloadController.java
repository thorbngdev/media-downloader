package com.thor.mediadownloader.controller;

import com.github.kiulian.downloader.model.videos.VideoDetails;
import com.thor.mediadownloader.service.DownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@MediaDownloaderApiV1
public class DownloadController {

    private static final Logger logger = LoggerFactory.getLogger(DownloadController.class);

    private static final String AUDIO_DOWNLOAD_ENDPOINT = "/download/audio";
    private static final String VIDEO_DOWNLOAD_ENDPOINT = "/download/video";
    private static final String VIDEO_WITHOUT_AUDIO_DOWNLOAD_ENDPOINT = "/download/no-audio-video";

    @Autowired
    private DownloadService downloadService;

    @GetMapping(value = AUDIO_DOWNLOAD_ENDPOINT)
    public ResponseEntity<String> audioDownloadEndpoint(@RequestParam String url) {
        logger.info("Downloading -> {}...", url);

        return ResponseEntity.ok(downloadService.downloadAudio(url));
    }

    @GetMapping(value = VIDEO_DOWNLOAD_ENDPOINT)
    public ResponseEntity<String> videoDownloadEndpoint(@RequestParam String url) {
        logger.info("Downloading -> {}...", url);

        return ResponseEntity.ok(downloadService.downloadVideo(url));
    }

    @GetMapping(value = VIDEO_WITHOUT_AUDIO_DOWNLOAD_ENDPOINT)
    public ResponseEntity<String> videoWithoutAudioDownloadEndpoint(@RequestParam String url) {
        logger.info("Downloading -> {}...", url);

        return ResponseEntity.ok(downloadService.downloadVideoWithoutAudio(url));
    }
}
