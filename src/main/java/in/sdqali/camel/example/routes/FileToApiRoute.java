package in.sdqali.camel.example.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.springframework.stereotype.Component;

@Component
public class FileToApiRoute extends RouteBuilder {

    public static final String UTF_8 = "UTF-8";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    public static final String TARGET = "http://localhost:8080/ingest";
    public static final String TARGET_WITH_AUTH = "http://localhost:8080/ingest" +
            "?authMethod=Basic&authUsername=test&authPassword=test";

    @Override
    public void configure() throws Exception {
        from("direct:start")
                .marshal()
                .string(UTF_8)
                .split(body().tokenize())
                .streaming()
                .setHeader(CONTENT_TYPE, simple(APPLICATION_JSON))
                .setHeader(Exchange.HTTP_METHOD, HttpMethods.PUT)
                .to(TARGET_WITH_AUTH);
    }

}