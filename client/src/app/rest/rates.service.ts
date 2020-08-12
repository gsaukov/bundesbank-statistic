import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {RateData} from './model/rateData';
import {ExchangeRequest} from './model/exchangeRequest';
import {ExchangeResponse} from './model/exchangeResponse';

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

  doExchange(currencyId: string, exchangeRequest: ExchangeRequest): Observable<ExchangeResponse> {
    return this.http.post<ExchangeResponse>(`/api/v1/rates/${currencyId}/exchange`, exchangeRequest)
  }

}
