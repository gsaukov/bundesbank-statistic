package com.bundesbank.boiler.service.load;

import com.bundesbank.boiler.persistance.entity.RateData;
import com.bundesbank.boiler.persistance.repository.RateDataRepository;
import de.bundesbank.statistik.zeitreihen.bbkcompact.ObsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class RateDataSaver {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    private RateDataRepository repository;

    public void saveRateData(List<ObsType> seriesType, BankCurrency currency, String bbkId) {
        List<RateData> data = new ArrayList<>();
        for(ObsType obsType : seriesType){
            RateData rateData = new RateData();
            rateData.setCurrencyId(currency);
            rateData.setBbkId(bbkId);
            rateData.setTimePeriod(parseDate(obsType.getTIMEPERIOD()));
            if(obsType.getBBKOBSSTATUS() != null){
                rateData.setBbkObsStatus(obsType.getBBKOBSSTATUS().value());
            }
            if(obsType.getOBSVALUE() != null) {
                rateData.setObsValue(new BigDecimal(obsType.getOBSVALUE()));
            }
            if(obsType.getBBKDIFF() != null) {
                rateData.setBbkDiff(new BigDecimal(obsType.getBBKDIFF()));
            }
            data.add(rateData);
        }
        repository.saveAll(data);
    }

    private LocalDate parseDate(String input){
        return LocalDate.parse(input, formatter);
    }
}
