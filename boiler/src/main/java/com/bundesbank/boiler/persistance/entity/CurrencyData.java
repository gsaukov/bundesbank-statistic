package com.bundesbank.boiler.persistance.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Access(AccessType.FIELD)
@Table(name = "CURRENCY_DATA")
public class CurrencyData {

    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "CURRENCY_ID")
    private String currencyId;
    @Column(name = "BBK_ID")
    private String bbkId;
    @Column(name = "TIME_PERIOD")
    private LocalDateTime timePeriod;
    @Column(name = "BBK_OBS_STATUS")
    private String bbkObsStatus;
    @Column(name = "OBS_VALUE")
    private BigDecimal obsValue;
    @Column(name = "BBK_DIFF")
    private BigDecimal bbkDiff;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getBbkId() {
        return bbkId;
    }

    public void setBbkId(String bbkId) {
        this.bbkId = bbkId;
    }

    public LocalDateTime getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(LocalDateTime timePeriod) {
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
