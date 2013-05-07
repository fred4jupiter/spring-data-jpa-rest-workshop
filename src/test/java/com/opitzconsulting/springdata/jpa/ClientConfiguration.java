package com.opitzconsulting.springdata.jpa;


//import org.codehaus.jackson.map.DeserializationConfig.Feature;
//import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class ClientConfiguration {

    static final String BASE_URL = "http://localhost:8080";

    static final String CUSTOMERS_REL = "customer";

    @Bean
    public RestOperations restOperations() {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
//        MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();
//        converter.setObjectMapper(mapper);
//        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
//
//        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
//        converters.add(converter);

        RestTemplate template = new RestTemplate();
//        template.setMessageConverters(converters);

        return template;
    }
}
