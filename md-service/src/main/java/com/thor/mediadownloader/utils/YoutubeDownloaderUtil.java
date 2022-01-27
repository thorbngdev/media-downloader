package com.thor.mediadownloader.utils;

import com.github.kiulian.downloader.Config;
import com.github.kiulian.downloader.YoutubeDownloader;

public class YoutubeDownloaderUtil {

    public static YoutubeDownloader instance() {
        Config config = new Config.Builder().maxRetries(1).build();
        return new YoutubeDownloader(config);
    }

}
