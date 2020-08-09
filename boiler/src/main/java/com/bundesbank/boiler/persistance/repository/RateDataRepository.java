package com.bundesbank.boiler.persistance.repository;

import com.bundesbank.boiler.persistance.entity.RateData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RateDataRepository extends JpaRepository<RateData, String> {

    @Query(value = "select rd.currencyId, max(rd.timePeriod) from RateData rd" +
            " group by rd.currencyId")
    List<LastUpdatedRateData> findLastUpdated ();

    interface LastUpdatedRateData {
        String getCurrencyId();
        LocalDate getTimePeriod();
    }

}
