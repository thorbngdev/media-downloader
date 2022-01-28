import { DownloadService } from './../service/download-service';
import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'download-page',
    templateUrl: './download-page.component.html',
    styleUrls: ['./download-page.component.scss']
})
export class DownloadPageComponent implements OnInit {

    /**
     * Video Info Props
        String videoId;
        String title;
        String author;
        List<String> thumbnails;
        String description;
        long viewCount;
        int averageRating;
        List<String> keywords;
     */
    videoInfo: any = undefined;

    constructor(private downloadService: DownloadService) { }

    ngOnInit(): void {

    }


    clickForDownload(url: string) {
        this.downloadService.getVideoInfo(url).subscribe(videoInfoDetail => {
            console.log(videoInfoDetail); //todo -> retirar consolelog
            this.videoInfo = videoInfoDetail;
        }, e => {
            window.alert('Ocorreu um erro ao carregar este vídeo, por favor verifique se o link inserido está correto');
        });
    }

    download(url: string, format: string) {
        this.downloadService.download(url, format).subscribe( (response: any) => {
            let filename = this.videoInfo.title.concat('.m4a');
            let dataType = response.type;
            let binaryData = [];
            binaryData.push(response);
            let downloadLink = document.createElement('a');
            downloadLink.href = window.URL.createObjectURL(new Blob(binaryData, {type: dataType}));
            if (filename)
                downloadLink.setAttribute('download', filename);
            document.body.appendChild(downloadLink);
            downloadLink.click();
        }, e => {
            window.alert('Ocorreu um erro ao carregar este vídeo, por favor verifique se o link inserido está correto');
        });
    }

}
