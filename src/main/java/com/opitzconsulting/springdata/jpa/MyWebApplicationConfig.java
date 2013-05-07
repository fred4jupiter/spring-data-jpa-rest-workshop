package com.opitzconsulting.springdata.jpa;

import com.opitzconsulting.springdata.jpa.util.SampleDataProvider;
import com.opitzconsulting.springdata.jpa.validation.CustomerValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;


@Configuration
@EnableJpaRepositories
@ComponentScan(basePackageClasses = SampleDataProvider.class)
@Import(InfrastructureConfig.class)
public class MyWebApplicationConfig extends RepositoryRestMvcConfiguration {

    // add custom Spring Data REST configuration here

    @Bean
    public CustomerValidator beforeCreateCustomerValidator() {
        return new CustomerValidator();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource msgsrc = new ReloadableResourceBundleMessageSource();
        msgsrc.setBasename("/WEB-INF/classes/ValidationMessages");
        msgsrc.setFallbackToSystemLocale(false);
        return msgsrc;
    }

}
