import { Component, OnInit } from '@angular/core';
import {DataService} from '../../shared/data.service';
import {RateData} from '../../rest/model/rateData';

@Component({
  selector: 'app-rates-graph-page',
  templateUrl: './rates-graph-page.component.html',
  styleUrls: ['./rates-graph-page.component.css']
})
export class RatesGraphPageComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
