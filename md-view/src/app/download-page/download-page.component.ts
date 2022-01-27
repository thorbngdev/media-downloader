import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'download-page',
    templateUrl: './download-page.component.html',
    styleUrls: ['./download-page.component.scss']
})
export class DownloadPageComponent implements OnInit {

    res: string = '';

    constructor() { }

    ngOnInit(): void {
    }

    download() {
        this.res = 'KUJlsBTsZTE';
    }

}
