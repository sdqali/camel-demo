package in.sdqali.camel.example.services;

import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DataStreamService {

    private final ProducerTemplate producerTemplate;

    @Autowired
    public DataStreamService(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    public void stream() {
        producerTemplate.sendBody("direct:start",
                ExchangePattern.InOnly,
                new File("src/main/resources/yelp.business.json.1"));
    }
}
