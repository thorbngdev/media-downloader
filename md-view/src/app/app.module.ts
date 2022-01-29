import { DownloadService } from './service/download-service';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DownloadPageComponent } from './download-page/download-page.component';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgProgressModule } from "ngx-progressbar";
import { NgProgressHttpModule } from "ngx-progressbar/http";

@NgModule({
  declarations: [
    AppComponent,
    DownloadPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NgProgressModule.withConfig({
      color: "#42f542",
      thick: true
    }),
    NgProgressHttpModule
  ],
  providers: [DownloadService],
  bootstrap: [AppComponent]
})
export class AppModule { }
