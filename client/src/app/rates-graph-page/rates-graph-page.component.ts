import { Component, OnInit } from '@angular/core';
import {RatesTablePageComponent} from '../rates-table-page/rates-table-page.component';
import {DataService} from '../shared/data.service';
import {RateData} from '../rest';

//D3,AccPerfChart will be in browser window.
declare var d3
declare var AccPerfChart

export interface ChartData {
  name: string
  values: CurrencyDayData []
}

export interface CurrencyDayData {
  date: any
  value: number
}

@Component({
  selector: 'app-rates-graph-page',
  templateUrl: './rates-graph-page.component.html',
  styleUrls: ['./rates-graph-page.component.css']
})
export class RatesGraphPageComponent implements OnInit {

  constructor(private dataService: DataService) { }

  ngOnInit(): void {
    const rateData: RateData [] = this.dataService.getData();
    const parsed3Date = d3.timeParse('%Y-%m-%d')

    let dayData: CurrencyDayData [] = [];
    for (let i = 0; i < rateData.length; i++) {
      let data: CurrencyDayData = {
        date : parsed3Date(rateData[i].timePeriod),
        value : rateData[i].obsValue
      }
      dayData[i] = data
    }

    const chartData : ChartData = {
      name: rateData[0].currencyId,
      values: dayData
    };
    AccPerfChart.bindData(chartData, this.createChartObj())
  }

  createChartObj():any {
    let chartObj:any = {};
    chartObj.margin = { top: 100, right: 70, bottom: 30, left: 50 };
    chartObj.chart = d3.select('#rateGraph');
    chartObj.svg = chartObj.chart.append('svg');
    chartObj.plot = chartObj.svg.append('g').attr('transform', `translate(${chartObj.margin.left}, ${chartObj.margin.top})`);
    // chartObj.parseDate = d3.timeParse('%Y-%m-%d');


    chartObj.x = d3.scaleTime();
    chartObj.y = d3.scaleLinear();

    chartObj.colour = d3.scaleOrdinal(d3.schemeCategory10);

    chartObj.xAxis = d3.axisBottom().scale(chartObj.x).ticks(10);
    chartObj.yAxis = d3.axisLeft().scale(chartObj.y).ticks(10);

    chartObj.line = d3.line()
      .x(d => chartObj.x(d.date))
      .y(d => chartObj.y(d.value))
      .curve(d3.curveMonotoneX);

    return chartObj
  }

}
