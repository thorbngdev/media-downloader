import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable()
export class HealthCheckService {

    headers = new HttpHeaders();

    constructor(private httpClient: HttpClient) {
    }

    healthCheck() {
        return this.httpClient.get(`${environment.apiUrl}/health-check`,
        {
            headers: this.headers
        });
    }

}