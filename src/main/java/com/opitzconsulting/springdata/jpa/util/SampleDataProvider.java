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

import javax.annotation.PostConstruct;

/**
 * Populates demo data at startup to query some data at runtime.
 */
@Component
public class SampleDataProvider {

    private static final Logger LOG = LoggerFactory.getLogger(SampleDataProvider.class);

    @Autowired
    private CustomerRepository customerRepository;

    @PostConstruct
    public void populateDemoCustomers() {
        Assert.notNull(customerRepository, "CustomerRepository must not be null!");
        LOG.info("populating demo customers...");

        Customer customer1 = new Customer("Fred", "Feuerstein");
        customer1.addAddress(new Address("Milchstrasse", "Hamburg", "Deutschland"));
        saveCustomer(customer1);

        saveCustomer(new Customer("Wilma", "Feuerstein"));
        saveCustomer(new Customer("Gertrud", "Fischer"));
        saveCustomer(new Customer("Teobald", "Frosch"));
        saveCustomer(new Customer("Gerlinde", "Furchbar"));
        saveCustomer(new Customer("Karl", "Katze"));
        saveCustomer(new Customer("Bert", "Bernstein"));
        saveCustomer(new Customer("Elke", "Olle"));
    }

    private void saveCustomer(Customer customer1) {
        customerRepository.save(customer1);
    }
}
