package com.bundesbank.boiler.config;


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

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        try {
            currencyDataLoader.loadCurrencyData();
        } catch (JAXBException | XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }

}
