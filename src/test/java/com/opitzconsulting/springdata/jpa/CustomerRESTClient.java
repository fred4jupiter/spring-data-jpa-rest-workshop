package com.opitzconsulting.springdata.jpa;

import com.opitzconsulting.springdata.jpa.domain.Customer;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;


public class CustomerRESTClient {

    static final String BASE_URL = "http://localhost:8080";

    static final String CUSTOMERS_REL = "customer";

    public static void main(String[] args) {
        RestOperations restOperations = new RestTemplate();

        // Access root resource
        ResourceSupport result = restOperations.getForObject(CustomerRESTClient.BASE_URL, Resource.class);

        Link link = result.getLink(CustomerRESTClient.CUSTOMERS_REL);
        System.out.println("Following: " + link.getHref());

        // Follow link relation for customers to access those
        CustomersPagedResources customers = restOperations.getForObject(link.getHref(), CustomersPagedResources.class);

        for (CustomerResource dto : customers) {
            Customer customer = dto.getContent();
            System.out.println(customer);
        }
    }

    static class CustomerResource extends Resource<Customer> {

    }

    static class CustomersPagedResources extends PagedResources<CustomerResource> {

    }
}
