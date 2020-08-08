package com.bundesbank.boiler.service.load;

import de.bundesbank.statistik.zeitreihen.bbkcompact.CompactData;
import de.bundesbank.statistik.zeitreihen.bbkcompact.ObsType;
import de.bundesbank.statistik.zeitreihen.bbkcompact.SeriesType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class CurrencyDataLoader {

    private static final Logger LOG = LoggerFactory.getLogger(CurrencyDataLoader.class);
    private static final String REST_URI = "https://www.bundesbank.de/statistic-rmi/StatisticDownload";
    private static final String PREFIX = "BBEX3.D.";
    private static final String POSTFIX = ".EUR.BB.AC.000";
    private final RestTemplate client = new RestTemplate();
    private final Unmarshaller unmarshaller;

    public CurrencyDataLoader() throws JAXBException {
        this.unmarshaller = createUnmarshaller();
    }

    public void loadCurrencyData() throws JAXBException, XMLStreamException, IOException {
        for(BankCurrencies cur : BankCurrencies.values()){
            CompactData compactData = loadCurrencyData(cur.name());
            SeriesType seriesType = (SeriesType) compactData.getDataSet().getSeriesAndAnnotations().get(0);
            List<ObsType> obsTypes = seriesType.getObs();
            LOG.info("loaded " + cur + " size " + obsTypes.size());
            persistCurrencies(obsTypes);
        }
    }

    private void persistCurrencies(List<ObsType> seriesType) {

    }

    private CompactData loadCurrencyData (String currency) throws JAXBException, XMLStreamException, IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_XML_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(REST_URI)
                .queryParam("tsId", getCurrencyQueryParam(currency))
                .queryParam("its_fileFormat", "sdmx")
                .queryParam("mode", "its");

        HttpEntity<Resource> response = client.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                new HttpEntity<>(headers),
                Resource.class);

        return unmarshalStream(response.getBody().getInputStream());
    }

    private String getCurrencyQueryParam(String currency) {
        return PREFIX + currency + POSTFIX;
    }

    private CompactData unmarshalStream (InputStream is) throws JAXBException, XMLStreamException {
        JAXBElement<CompactData> el =  unmarshaller.unmarshal(new StreamSource(is), CompactData.class);
        return el.getValue();
    }

    private static Unmarshaller createUnmarshaller() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext
                .newInstance(
                        de.bundesbank.statistik.zeitreihen.bbkcompact.ObjectFactory.class,
                        org.sdmx.resources.sdmxml.schemas.v2_0.message.ObjectFactory.class,
                        org.sdmx.resources.sdmxml.schemas.v2_0.compact.ObjectFactory.class,
                        org.sdmx.resources.sdmxml.schemas.v2_0.common.ObjectFactory.class,
                        org.sdmx.resources.sdmxml.schemas.v2_0.metadatareport.ObjectFactory.class,
                        org.sdmx.resources.sdmxml.schemas.v2_0.genericmetadata.ObjectFactory.class,
                        org.sdmx.resources.sdmxml.schemas.v2_0.cross.ObjectFactory.class,
                        org.sdmx.resources.sdmxml.schemas.v2_0.generic.ObjectFactory.class,
                        org.sdmx.resources.sdmxml.schemas.v2_0.utility.ObjectFactory.class
                );
        return jaxbContext.createUnmarshaller();
    }


}
