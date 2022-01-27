package com.thor.mediadownloader.controller;

import com.thor.mediadownloader.service.DownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@MediaDownloaderApiV1
public class DownloadController {

    private static final Logger logger = LoggerFactory.getLogger(DownloadController.class);

    private static final String DOWNLOAD_ENDPOINT = "/download/{format}";

    @Autowired
    private DownloadService downloadService;

    @GetMapping(value = DOWNLOAD_ENDPOINT)
    public ResponseEntity<String> audioDownloadEndpoint(@PathVariable String format, @RequestParam String url) {
        logger.info("Downloading -> {}...", url);

        return ResponseEntity.ok(downloadService.download(url, format));
    }
}
