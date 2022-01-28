package com.thor.mediadownloader.utils;

import com.github.kiulian.downloader.Config;
import com.github.kiulian.downloader.YoutubeDownloader;

import java.util.concurrent.TimeUnit;

public class YoutubeDownloaderUtil {

    public static YoutubeDownloader instance() {
        Config config = new Config.Builder().maxRetries(1).build();
        return new YoutubeDownloader(config);
    }

    public static String getVideoDuration(long seconds) {
        long hours = TimeUnit.SECONDS.toHours(seconds);
        seconds -= TimeUnit.HOURS.toSeconds(hours);
        long minutes = TimeUnit.SECONDS.toMinutes(seconds);
        seconds -= TimeUnit.MINUTES.toSeconds(minutes);

        return MdStringUtils.addLeftZeroToTimeField(hours).concat(":") +
               MdStringUtils.addLeftZeroToTimeField(minutes).concat(":") +
               MdStringUtils.addLeftZeroToTimeField(seconds);
    }

}
