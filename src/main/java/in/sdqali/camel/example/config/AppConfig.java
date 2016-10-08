package in.sdqali.camel.example.config;

import in.sdqali.camel.example.services.DataStreamService;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.http.HttpComponent;
import org.apache.camel.http.common.HttpConfiguration;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/application.properties")
public class AppConfig {
    @Value("${auth.scheme}")
    String authScheme;

    @Value("${auth.username}")
    String authUsername;

    @Value("${auth.password}")
    String authPassword;

    @Autowired
    ProducerTemplate producerTemplate;

    @Bean
    DataStreamService dataStreamService() {
        return new DataStreamService(producerTemplate);
    }

    @Bean
    CamelContextConfiguration contextConfiguration() {
        return camelContext -> {
            camelContext.setTracing(true);
            HttpComponent http = (HttpComponent) camelContext.getComponent("http");
            http.setHttpConfiguration(httpConfiguration());
        };
    }

    private HttpConfiguration httpConfiguration() {
        HttpConfiguration httpConfiguration = new HttpConfiguration();
        httpConfiguration.setAuthMethod(authScheme);
        httpConfiguration.setAuthUsername(authUsername);
        httpConfiguration.setAuthPassword(authPassword);
        return httpConfiguration;
    }
}