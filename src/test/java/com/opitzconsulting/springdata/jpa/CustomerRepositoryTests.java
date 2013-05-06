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


public class CustomerRepositoryTests extends AbstractTestingBase {

    @Test
    public void callRESTInterfaceOfCustomers() {
        RestOperations restOperations = getRestOperations();

        // Access root resource
        ResourceSupport result = restOperations.getForObject(ClientConfiguration.BASE_URL, Resource.class);

        Link link = result.getLink(ClientConfiguration.CUSTOMERS_REL);
        System.out.println("Following: " + link.getHref());

        // Follow link relation for customers to access those
        CustomersPagedResources customers = restOperations.getForObject(link.getHref(), CustomersPagedResources.class);

        for (CustomerResource dto : customers) {
            Customer customer = dto.getContent();
            System.out.println(customer);
        }
    }

    private RestOperations getRestOperations() {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(ClientConfiguration.class);
        context.registerShutdownHook();
        return context.getBean(RestOperations.class);
    }

    static class CustomerResource extends Resource<Customer> {

    }

    static class CustomersPagedResources extends PagedResources<CustomerResource> {

    }
}
