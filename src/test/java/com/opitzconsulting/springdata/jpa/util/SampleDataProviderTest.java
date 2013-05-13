package com.opitzconsulting.springdata.jpa.util;

import com.opitzconsulting.springdata.jpa.MyWebApplicationConfig;
import com.opitzconsulting.springdata.jpa.domain.Address;
import com.opitzconsulting.springdata.jpa.domain.Customer;
import com.opitzconsulting.springdata.jpa.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@ContextConfiguration(classes = MyWebApplicationConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SampleDataProviderTest {

    @Autowired
    private SampleDataProvider sampleDataProvider;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void populateDemoDataAndCheckIfPersisted() {
        assertNotNull(sampleDataProvider);

        Page<Customer> allCustomers = customerRepository.findAll(new PageRequest(1, 50));
        assertThat(allCustomers.getTotalElements(), equalTo(8L));
    }

    @Test
    public void checkIfOneCustomerHasAnAddress() {
        assertNotNull(sampleDataProvider);

        final String firstname = "Fred";
        List<Customer> customers = customerRepository.findByFirstname(firstname);
        assertThat(customers.size(), equalTo(1));

        Customer customer = customers.get(0);
        assertNotNull(customer);
        assertThat(customer.getFirstname(), equalTo(firstname));
        Set<Address> addresses = customer.getAddresses();
        assertThat(addresses.size(), equalTo(1));
        Address address = addresses.iterator().next();
        assertNotNull(address);
    }
}
