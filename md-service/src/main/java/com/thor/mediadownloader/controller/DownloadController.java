package com.thor.mediadownloader.controller;

import com.github.kiulian.downloader.model.videos.VideoInfo;
import com.thor.mediadownloader.model.VideoInfoDetail;
import com.thor.mediadownloader.service.DownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@MediaDownloaderApiV1
public class DownloadController {

    private static final Logger logger = LoggerFactory.getLogger(DownloadController.class);

    private static final String DOWNLOAD_ENDPOINT = "/download/{format}";

    private static final String VIDEO_DETAILS_ENDPOINT = "/video-info";

    @Autowired
    private DownloadService downloadService;

    @GetMapping(value = VIDEO_DETAILS_ENDPOINT)
    public ResponseEntity<VideoInfoDetail> getVideoInfo(@RequestParam String url) {
        return ResponseEntity.ok(downloadService.getVideoInfo(url));
    }

    @GetMapping(value = DOWNLOAD_ENDPOINT)
    public ResponseEntity<Resource> downloadEndpoint(@PathVariable String format, @RequestParam String url) throws IOException {
        logger.info("Downloading -> {}...", url);
        File file = downloadService.download(url, format);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
