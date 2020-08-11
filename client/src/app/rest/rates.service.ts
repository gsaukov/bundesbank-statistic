import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {RateData} from './model/rateData';

@Injectable({
  providedIn: 'root'
})
export class RatesService {
  constructor(private http: HttpClient) {
  }

  fetchCurrencies(): Observable<string[]> {
    return this.http.get<string[]>(`/api/v1/currencies/`)
  }

  fetchRates(currencyId: string): Observable<RateData[]> {
    return this.http.get<RateData[]>(`/api/v1/rates/${currencyId}`)
  }


}
