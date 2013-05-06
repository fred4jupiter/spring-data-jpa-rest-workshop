package com.opitzconsulting.springdata.jpa;

import com.opitzconsulting.springdata.jpa.util.SampleDataProvider;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Application specific Java configuration.
 * <p/>
 * <p>NOTE: There is not auditing java config available yet.
 * @see <a href="https://jira.springsource.org/browse/DATAJPA-265">https://jira.springsource.org/browse/DATAJPA-265</a> for details.
 */
@Configuration
@EnableJpaRepositories
@ComponentScan(basePackageClasses = SampleDataProvider.class)
@Import(InfrastructureConfig.class)
public class ApplicationConfig {

}
