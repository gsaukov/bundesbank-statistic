package com.bundesbank.boiler.persistance.entity;

import com.bundesbank.boiler.service.load.BankCurrency;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Access(AccessType.FIELD)
@Table(name = "RATE_DATA")
public class RateData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "CURRENCY_ID")
    private BankCurrency currencyId;
    @Column(name = "BBK_ID")
    private String bbkId;
    @Column(name = "TIME_PERIOD")
    private LocalDate timePeriod;
    @Column(name = "BBK_OBS_STATUS")
    private String bbkObsStatus;
    @Column(name = "OBS_VALUE")
    private BigDecimal obsValue;
    @Column(name = "BBK_DIFF")
    private BigDecimal bbkDiff;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BankCurrency getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(BankCurrency currencyId) {
        this.currencyId = currencyId;
    }

    public String getBbkId() {
        return bbkId;
    }

    public void setBbkId(String bbkId) {
        this.bbkId = bbkId;
    }

    public LocalDate getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(LocalDate timePeriod) {
        this.timePeriod = timePeriod;
    }

    public String getBbkObsStatus() {
        return bbkObsStatus;
    }

    public void setBbkObsStatus(String bbkObsStatus) {
        this.bbkObsStatus = bbkObsStatus;
    }

    public BigDecimal getObsValue() {
        return obsValue;
    }

    public void setObsValue(BigDecimal obsValue) {
        this.obsValue = obsValue;
    }

    public BigDecimal getBbkDiff() {
        return bbkDiff;
    }

    public void setBbkDiff(BigDecimal bbkDiff) {
        this.bbkDiff = bbkDiff;
    }
}
