import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable()
export class DownloadService {

    headers = new HttpHeaders();

    constructor(private httpClient: HttpClient) {
        this.headers.append('Content-Type', 'application/json');
    }

    getVideoInfo(url: string) {
        return this.httpClient.get(`${environment.apiUrl}/video-info`,
        {
            headers: this.headers,
            params: {
                url: url
            }
        });
    }

    download(downloadUrl: string, format: string) {
        
        return this.httpClient.get(`${environment.apiUrl}/download/${format}`,
        {
            headers: this.headers, 
            responseType: 'blob', 
            reportProgress: true,
            params: {
                url: downloadUrl
            }
        });
    }

}