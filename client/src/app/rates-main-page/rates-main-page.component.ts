import {AfterViewInit, Component, OnInit} from '@angular/core';
import {DataService} from '../shared/data.service';

@Component({
  selector: 'app-rates-main-page',
  templateUrl: './rates-main-page.component.html',
  styleUrls: ['./rates-main-page.component.css']
})
export class RatesMainPageComponent implements OnInit, AfterViewInit {

  constructor(private dataService: DataService) {
  }

  ngOnInit(): void {
    this.dataService.updateData("USD")
  }

  ngAfterViewInit(): void {
  }

}
