package in.sdqali.camel.example;

import in.sdqali.camel.example.services.DataStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class MainApplication implements CommandLineRunner{
    @Autowired
    DataStreamService dataStreamService;

    public static void main(String[] args) {
       SpringApplication.run(MainApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        dataStreamService.stream();
    }

}
