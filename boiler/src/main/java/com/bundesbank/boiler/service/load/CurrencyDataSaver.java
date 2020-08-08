package com.bundesbank.boiler.service.load;

import com.bundesbank.boiler.persistance.entity.CurrencyData;
import com.bundesbank.boiler.persistance.repository.CurrencyDataRepository;
import de.bundesbank.statistik.zeitreihen.bbkcompact.ObsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyDataSaver {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    private CurrencyDataRepository repository;

    public void saveCurrencyData(List<ObsType> seriesType, BankCurrency currency, String bbkId) {
        List<CurrencyData> data = new ArrayList<>();
        for(ObsType obsType : seriesType){
            CurrencyData currencyData = new CurrencyData();
            currencyData.setCurrencyId(currency);
            currencyData.setBbkId(bbkId);
            currencyData.setTimePeriod(parseDate(obsType.getTIMEPERIOD()));
            if(obsType.getBBKOBSSTATUS() != null){
                currencyData.setBbkObsStatus(obsType.getBBKOBSSTATUS().value());
            }
            if(obsType.getOBSVALUE() != null) {
                currencyData.setObsValue(new BigDecimal(obsType.getOBSVALUE()));
            }
            if(obsType.getBBKDIFF() != null) {
                currencyData.setBbkDiff(new BigDecimal(obsType.getBBKDIFF()));
            }
            data.add(currencyData);
        }
        repository.saveAll(data);
    }

    private LocalDate parseDate(String input){
        return LocalDate.parse(input, formatter);
    }
}
