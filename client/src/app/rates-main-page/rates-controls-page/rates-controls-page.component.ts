import { Component, OnInit } from '@angular/core';
import {RatesService} from '../../rest/rates.service';

@Component({
  selector: 'app-rates-controls-page',
  templateUrl: './rates-controls-page.component.html',
  styleUrls: ['./rates-controls-page.component.css']
})
export class RatesControlsPageComponent implements OnInit {

  selectorData: string[]

  constructor(private ratesService: RatesService) { }

  ngOnInit(): void {
    this.ratesService.fetchCurrencies().subscribe(
      (data) => {
        this.selectorData = data
      }
    )
  }

}
