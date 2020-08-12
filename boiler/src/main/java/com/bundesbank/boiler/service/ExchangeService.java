package com.bundesbank.boiler.service;

import com.bundesbank.boiler.controller.ExchangeRequest;
import com.bundesbank.boiler.controller.ExchangeResponse;
import com.bundesbank.boiler.persistance.entity.RateData;
import com.bundesbank.boiler.persistance.repository.RateDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeService {

    @Autowired
    private RateDataRepository repository;

    public ExchangeResponse exchange (String currencyId, ExchangeRequest exchangeRequest){
        RateData rateData = repository.findMostRecentToDate(currencyId, exchangeRequest.getDate());
        return buildExchangeResponse(exchangeRequest, rateData);
    }

    private ExchangeResponse buildExchangeResponse(ExchangeRequest exchangeRequest, RateData rateData) {
        ExchangeResponse response = new ExchangeResponse();
        if(rateData == null) {
            response.setStatus(ExchangeResponse.Status.NO_DATA);
        } else {
            response.setCurrency(rateData.getCurrencyId().name());
            response.setRate(rateData.getObsValue());
            response.setRequestDate(exchangeRequest.getDate());
            response.setActualDate(rateData.getTimePeriod());
            response.setOriginalAmount(exchangeRequest.getAmount());
            response.setExchangedAmount(exchangeRequest.getAmount().multiply(rateData.getObsValue()));
            response.setStatus(ExchangeResponse.Status.OK);
        }
        return response;
    }
}
