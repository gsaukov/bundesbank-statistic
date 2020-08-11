import {AfterViewInit, Component, OnInit} from '@angular/core';
import {RatesService} from '../rest/rates.service';
import {DataService} from '../shared/data.service';
import {GraphService} from './rates-graph-page/graph.service';

@Component({
  selector: 'app-rates-main-page',
  templateUrl: './rates-main-page.component.html',
  styleUrls: ['./rates-main-page.component.css']
})
export class RatesMainPageComponent implements OnInit, AfterViewInit {

  constructor(private dataService: DataService,
              private graphService: GraphService,
              private ratesService: RatesService) {
  }

  ngOnInit(): void {
    this.ratesService.fetchRates("USD").subscribe(
      (data) => {
        this.dataService.updateData(data)
        this.graphService.rebuildChart(data)
      },
      (error) => {
        console.log(error)
      }
    )
  }

  ngAfterViewInit(): void {
  }

}
