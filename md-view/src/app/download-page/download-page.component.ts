import { DownloadService } from './../service/download-service';
import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'download-page',
    templateUrl: './download-page.component.html',
    styleUrls: ['./download-page.component.scss']
})
export class DownloadPageComponent implements OnInit {

    //gold and oden btw
    readonly exampleUrl = 'https://www.youtube.com/watch?v=6FscTa_Nqwo';

    //example format, have 3: audio, video (original), video with no sound
    readonly exampleFormat = 'video';

    res: string = '';

    constructor(private downloadService: DownloadService) { }

    ngOnInit(): void {

    }


    clickForDownload(url: string) {
        this.downloadService.getVideoInfo(url).subscribe(videoInfo => {
            /*
                String videoId;
                String title;
                String author;
                List<String> thumbnails;
                String description;
                long viewCount;
                int averageRating;
                List<String> keywords;
            */
            console.log(videoInfo);
            this.download(videoInfo, url);
        });
    }

    private download(videoInfo: any, url: string) {
        this.downloadService.download(url, this.exampleFormat).subscribe( (response: any) => {
            let filename = videoInfo.title.concat('.m4a');
            let dataType = response.type;
            let binaryData = [];
            binaryData.push(response);
            let downloadLink = document.createElement('a');
            downloadLink.href = window.URL.createObjectURL(new Blob(binaryData, {type: dataType}));
            if (filename)
                downloadLink.setAttribute('download', filename);
            document.body.appendChild(downloadLink);
            downloadLink.click();
        });
    }

}
