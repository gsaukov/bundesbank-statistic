package com.bundesbank.boiler.controller;

import com.bundesbank.boiler.persistance.repository.RateDataRepository;
import com.bundesbank.boiler.service.load.BankCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
public class CurrencyController {

    @Autowired
    private RateDataRepository repository;

    @GetMapping("/v1/currencies")
    public List<String> getCurrencies(){
        return Stream.of(BankCurrency.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/v1/currencies/status")
    public List<RateDataRepository.LastUpdatedRateData> getCurrencyStatuses(){
        return repository.findLastUpdated();
    }

}
