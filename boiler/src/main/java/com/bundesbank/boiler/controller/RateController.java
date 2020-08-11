package com.bundesbank.boiler.controller;

import com.bundesbank.boiler.persistance.entity.RateData;
import com.bundesbank.boiler.persistance.repository.RateDataRepository;
import com.bundesbank.boiler.service.ExchangeService;
import com.bundesbank.boiler.service.load.BankCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class RateController {

    @Autowired
    private RateDataRepository repository;

    @Autowired
    private ExchangeService exchangeService;

    @GetMapping("/v1/rates/{currencyId}")
    public List<RateData> getRate(@PathVariable String currencyId){
        return repository.findByCurrencyIdAndBbkObsStatus(getBankCurrency(currencyId), null);
    }

    @PostMapping("/v1/rates/{currencyId}/exchange")
    public ExchangeResponse exchange(@PathVariable String currencyId, @RequestBody ExchangeRequest request) {
        getBankCurrency(currencyId);
        return exchangeService.exchange(currencyId, request);
    }

    private BankCurrency getBankCurrency(String currencyId) {
        BankCurrency currency;
        try {
            currency = BankCurrency.valueOf(currencyId);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException(currencyId);
        }
        return currency;
    }
}
