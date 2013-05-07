package com.opitzconsulting.springdata.jpa.util;

import com.opitzconsulting.springdata.jpa.domain.Address;
import com.opitzconsulting.springdata.jpa.domain.Customer;
import com.opitzconsulting.springdata.jpa.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Populates demo data at startup to query some data at runtime.
 */
@Component
public class SampleDataProvider implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(SampleDataProvider.class);

    private final CustomerRepository customerRepository;

    @Autowired
    public SampleDataProvider(CustomerRepository customerRepository) {
        LOG.info("SampleDataProvider activated!");

        Assert.notNull(customerRepository, "CustomerRepository must not be null!");
        this.customerRepository = customerRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Assert.notNull(customerRepository, "CustomerRepository must not be null!");
        LOG.info("populating demo customers...");
        populateDemoCustomers();
    }

    private void populateDemoCustomers() {
        Customer customer1 = new Customer(500.00, "Fred", "Feuerstein", "fred@feuerstein.de");
        customer1.addAddress(new Address("Milchstrasse", "Hamburg", "Deutschland"));
        saveCustomer(customer1);

        saveCustomer(new Customer(546.34, "Wilma", "Feuerstein", "wilma@feuerstein.de"));
        saveCustomer(new Customer(12345.00, "Karl", "Katze", "karl@katze.de"));
        saveCustomer(new Customer(250.00, "Bert", "Bernstein", "bert@bernstein.de"));
        saveCustomer(new Customer(7596.00, "Elke", "Olle", "elke@olle.de"));
    }

    private void saveCustomer(Customer customer1) {
        customerRepository.saveAndFlush(customer1);
    }
}
