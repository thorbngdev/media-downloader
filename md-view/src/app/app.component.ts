import { HealthCheckService } from './service/health-check.service';
import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

    title = 'md-view';

    constructor(private healthCheckService: HealthCheckService) {}

    ngOnInit(): void {
        setInterval(() => this.healthCheckService
            .healthCheck()
            .subscribe(r => undefined, e => undefined), 10000)
    }
}
