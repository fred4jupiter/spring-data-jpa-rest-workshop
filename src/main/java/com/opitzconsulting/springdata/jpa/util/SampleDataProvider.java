package com.opitzconsulting.springdata.jpa.util;

import com.opitzconsulting.springdata.jpa.domain.Address;
import com.opitzconsulting.springdata.jpa.domain.Customer;
import com.opitzconsulting.springdata.jpa.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;


@Component
public class SampleDataProvider implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {

    private static final Logger LOG = LoggerFactory.getLogger(SampleDataProvider.class);

    private static final String SQL_FILE = "data.sql";

    private final CustomerRepository customerRepository;

    private ApplicationContext applicationContext;

    @Autowired
    public SampleDataProvider(CustomerRepository customerRepository) {
        LOG.info("SampleDataProvider activated!");

        Assert.notNull(customerRepository, "CustomerRepository must not be null!");
        this.customerRepository = customerRepository;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!event.getApplicationContext().equals(applicationContext)) {
            return;
        }

        LOG.info("populating demo customers...");
        populateDemoCustomers();
    }

    private void populateDemoCustomers() {
        Customer customer1 = new Customer(500.00, "Fred", "Feuerstein", "fred@feuerstein.de");
        Customer customer2 = new Customer(546.34, "Wilma", "Feuerstein", "wilma@feuerstein.de");
        Customer customer3 = new Customer(12345.00, "Karl", "Katze", "karl@katze.de");
        Customer customer4 = new Customer(250.00, "Bert", "Bernstein", "bert@bernstein.de");
        Customer customer5 = new Customer(7596.00, "Elke", "Olle", "elke@olle.de");

        customer1.addAddress(new Address("Milchstrasse", "Hamburg", "Deutschland"));
        customerRepository.saveAndFlush(customer1);
        customerRepository.saveAndFlush(customer2);
        customerRepository.saveAndFlush(customer3);
        customerRepository.saveAndFlush(customer4);
        customerRepository.saveAndFlush(customer5);
    }
}
