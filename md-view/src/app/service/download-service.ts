import { environment } from './../../environments/environment';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable()
export class DownloadService {

    constructor(private httpClient: HttpClient) {}

    download(downloadUrl: string, format: string) {
        let headers = new HttpHeaders();
        headers.append('Content-Type', 'audio/mp4');
        return this.httpClient.get(`${environment.apiUrl}/download/${format}?url=${downloadUrl}`,{headers: headers, responseType: 'blob' as 'json'});
    }

}