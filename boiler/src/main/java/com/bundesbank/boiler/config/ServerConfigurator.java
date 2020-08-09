package com.bundesbank.boiler.config;


import com.bundesbank.boiler.persistance.repository.RateDataRepository;
import com.bundesbank.boiler.service.load.RateDataLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

@Configuration
public class ServerConfigurator implements ApplicationListener<ApplicationReadyEvent> {

    private static Logger LOG = LoggerFactory.getLogger(ServerConfigurator.class);

    @Autowired
    private RateDataLoader rateDataLoader;

    @Autowired
    private RateDataRepository rateDataRepository;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        long count = rateDataRepository.count();
        if(count == 0){
            loadRateData();
        } else {
            printLastUpdated();
        }
    }

    private void loadRateData(){
        try {
            rateDataLoader.loadRateData();
        } catch (JAXBException | XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }

    private void printLastUpdated() {
        rateDataRepository.findLastUpdated().stream()
                .forEach(e -> LOG.info(e.getCurrencyId() + " last update " + e.getTimePeriod()));
    }

}
