import { environment } from './../../environments/environment';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable()
export class DownloadService {

    headers = new HttpHeaders();

    constructor(private httpClient: HttpClient) {
        this.headers.append('Content-Type', 'application/json');
        // this.headers.append('Content-Security-Policy', 'upgrade-insecure-requests');
    }

    getVideoInfo(url: string) {
        return this.httpClient.get(`${environment.apiUrl}/video-info?url=${url}`,
        {
            headers: this.headers
        });
    }

    download(downloadUrl: string, format: string) {
        
        return this.httpClient.get(`${environment.apiUrl}/download/${format}?url=${downloadUrl}`,
        {
            headers: this.headers, 
            responseType: 'blob', 
            reportProgress: true
        });
    }

}