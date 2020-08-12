import {RatesService} from '../rest/rates.service';
import {Injectable} from '@angular/core';
import {GraphService} from '../rates-main-page/rates-graph-page/graph.service';

@Injectable()
export class DataService {

  public dataSource;

  constructor(private graphService: GraphService,
              private ratesService: RatesService) {
  }

  public updateData(currencyId:string):void {
    this.ratesService.fetchRates(currencyId).subscribe(
      (data) => {
        this.dataSource = data
        this.graphService.rebuildChart(data)
      },
      (error) => {
        console.log(error)
      }
    )
  }
}
