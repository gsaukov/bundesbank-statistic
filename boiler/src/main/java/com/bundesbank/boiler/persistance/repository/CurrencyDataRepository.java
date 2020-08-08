package com.bundesbank.boiler.persistance.repository;

import com.bundesbank.boiler.persistance.entity.CurrencyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyDataRepository extends JpaRepository<CurrencyData, String> {

}
