import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {MaterialModule} from './material-module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { RatesTablePageComponent } from './rates-main-page/rates-table-page/rates-table-page.component';
import { RatesGraphPageComponent } from './rates-main-page/rates-graph-page/rates-graph-page.component';
import { RatesControlsPageComponent } from './rates-main-page/rates-controls-page/rates-controls-page.component';
import { RatesMainPageComponent } from './rates-main-page/rates-main-page.component';
import {DataService} from './shared/data.service';
import {HttpClientModule} from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent,
    RatesTablePageComponent,
    RatesGraphPageComponent,
    RatesControlsPageComponent,
    RatesMainPageComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    HttpClientModule
  ],
  providers: [DataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
