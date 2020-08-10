import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {MaterialModule} from './material-module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { RatesTablePageComponent } from './rates-table-page/rates-table-page.component';
import { RatesGraphPageComponent } from './rates-graph-page/rates-graph-page.component';
import { RatesControlsPageComponent } from './rates-controls-page/rates-controls-page.component';
import {DataService} from './shared/data.service';

@NgModule({
  declarations: [
    AppComponent,
    RatesTablePageComponent,
    RatesGraphPageComponent,
    RatesControlsPageComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule
  ],
  providers: [DataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
