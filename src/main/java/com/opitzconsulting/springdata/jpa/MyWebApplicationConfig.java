package com.opitzconsulting.springdata.jpa;

import com.opitzconsulting.springdata.jpa.util.SampleDataProvider;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;


@Configuration
@EnableJpaRepositories
@ComponentScan(basePackageClasses = SampleDataProvider.class)
@Import(InfrastructureConfig.class)
public class MyWebApplicationConfig extends RepositoryRestMvcConfiguration {

    // add custom Spring Data REST configuration here
}
