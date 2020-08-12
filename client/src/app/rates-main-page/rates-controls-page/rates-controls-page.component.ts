import { Component, OnInit } from '@angular/core';
import {RatesService} from '../../rest/rates.service';
import {DataService} from '../../shared/data.service';
import {ExchangeRequest} from '../../rest/model/exchangeRequest';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ExchangeResponse} from '../../rest/model/exchangeResponse';

@Component({
  selector: 'app-rates-controls-page',
  templateUrl: './rates-controls-page.component.html',
  styleUrls: ['./rates-controls-page.component.css']
})
export class RatesControlsPageComponent implements OnInit {

  neonGlow: boolean
  form: FormGroup
  selectorData: string[]
  selectorValue: string
  exchangeResponce: ExchangeResponse

  constructor(private ratesService: RatesService, public dataService: DataService) { }

  ngOnInit(): void {
    this.neonGlow = false;
    this.form = new FormGroup({
      amount: new FormControl(null, [Validators.required, Validators.min(1)]),
      date: new FormControl(null, [Validators.required])
    })
    this.ratesService.fetchCurrencies().subscribe(
      (data) => {
        this.selectorData = data
      }
    )
    this.selectorValue = 'USD'
  }

  onGetRates(){
    this.dataService.updateData(this.selectorValue)
  }

  onRateSelectorChange(value: string) {
    this.selectorValue = value;
  }

  onExchange(): void {
    this.exchangeResponce = null;
    const request:ExchangeRequest = {
      amount: this.form.value.amount,
      date: this.form.value.date
    };

    this.ratesService.doExchange(this.selectorValue, request).subscribe(
      async (responce) => {
        this.exchangeResponce = responce
        this.neonGlow = true;
        //we are async so its ok
        await delay(1000);
        this.neonGlow = false;
      }
    )

    function delay(ms: number) {
      return new Promise( resolve => setTimeout(resolve, ms) );
    }

  }
}
