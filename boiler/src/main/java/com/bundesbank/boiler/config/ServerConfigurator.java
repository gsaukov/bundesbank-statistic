package com.bundesbank.boiler.config;


import com.bundesbank.boiler.persistance.repository.CurrencyDataRepository;
import com.bundesbank.boiler.service.load.CurrencyDataLoader;
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
    private CurrencyDataLoader currencyDataLoader;

    @Autowired
    private CurrencyDataRepository currencyDataRepository;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        long count = currencyDataRepository.count();
        if(count == 0){
            loadCurrencyData();
        } else {
//            currencyDataRepository.
        }
    }

    private void loadCurrencyData(){
        try {
            currencyDataLoader.loadCurrencyData();
        } catch (JAXBException | XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }

}
