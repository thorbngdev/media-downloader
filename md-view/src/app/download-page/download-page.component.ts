import { DownloadService } from './../service/download-service';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { HttpEventType } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
    selector: 'download-page',
    templateUrl: './download-page.component.html',
    styleUrls: ['./download-page.component.scss']
})
export class DownloadPageComponent implements OnInit {
    
    videoInfo: any = undefined;
    isDownloading = false;

    innerWidth: any;
    innerHeight: any;

    @ViewChild('urlInput') input: ElementRef = new ElementRef('urlInput');

// public clear() {
//     this.input.NativeElement.value = '';
// }

    
    constructor(private downloadService: DownloadService) { }

    ngOnInit(): void {
        this.innerWidth = window.innerWidth;
        this.innerHeight = window.innerHeight;
    }

    clickForDownload(url: string) {
        this.input.nativeElement.value = '';
        if (!this.isDownloading) {
            this.isDownloading = true;
            this.downloadService.getVideoInfo(url).subscribe(videoInfoDetail => {
                this.videoInfo = videoInfoDetail;
                this.isDownloading = false;
            }, e => {
                window.alert('Ocorreu um erro ao carregar este vídeo, por favor verifique se o link inserido está correto');
                this.isDownloading = false;
            });
        }
    }

    download(url: string, format: string) {
        this.isDownloading = true;
        this.downloadService.download(url, format).subscribe( (response: any) => {
            this.isDownloading = false;
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
            this.isDownloading = false;
        });
    }

    getThumbnail() {
        if (window.innerWidth < 600) {
            return this.videoInfo.thumbnails[0];
        }

        if (window.innerWidth < 1480) {
            return this.videoInfo.thumbnails[1];
        }

        return this.videoInfo.thumbnails[2];
    }

    goToGithub() {
        window.open('https://github.com/thorbngdev/media-downloader');
    }

    goToLinkedin() {
        window.open('https://www.linkedin.com/in/eduardo-torres-galvao/');
    }

    getInfoIconStyle() {
        return this.isDownloading ? 'get-info-icon-downloading' : 'get-info-icon';
    }

}
