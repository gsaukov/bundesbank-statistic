package com.bundesbank.boiler.persistance.repository;

import com.bundesbank.boiler.persistance.entity.RateData;
import com.bundesbank.boiler.service.load.BankCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RateDataRepository extends JpaRepository<RateData, String> {

    List<RateData> findByCurrencyIdAndBbkObsStatus (BankCurrency currencyId, String bbkObsStatus);

    @Query(value = "select rd.currencyId as currencyId, max(rd.timePeriod) as timePeriod " +
            " from RateData rd group by rd.currencyId")
    List<LastUpdatedRateData> findLastUpdated ();

    interface LastUpdatedRateData {
        String getCurrencyId();
        LocalDate getTimePeriod();
    }

}
