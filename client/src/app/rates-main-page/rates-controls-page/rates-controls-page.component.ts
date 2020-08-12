import { Component, OnInit } from '@angular/core';
import {RatesService} from '../../rest/rates.service';
import {DataService} from '../../shared/data.service';

@Component({
  selector: 'app-rates-controls-page',
  templateUrl: './rates-controls-page.component.html',
  styleUrls: ['./rates-controls-page.component.css']
})
export class RatesControlsPageComponent implements OnInit {

  selectorData: string[]
  selectorValue: string

  constructor(private ratesService: RatesService, private dataService: DataService) { }

  ngOnInit(): void {
    this.ratesService.fetchCurrencies().subscribe(
      (data) => {
        this.selectorData = data
      }
    )
  }

  onGetRates(){
    this.dataService.updateData(this.selectorValue)
  }

  onRateSelectorChange(value: string) {
    this.selectorValue = value;
  }
}
