package com.opitzconsulting.springdata.jpa;

import com.opitzconsulting.springdata.jpa.domain.Address;
import com.opitzconsulting.springdata.jpa.domain.Customer;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;


public class AddressRESTClient {

    private static final String BASE_URL = "http://localhost:8080";

    private static final String ADDRESSES_REL = "addresses";

    public static void main(String[] args) {
        RestOperations restOperations = new RestTemplate();

        // Access root resource
        ResourceSupport result = restOperations.getForObject(AddressRESTClient.BASE_URL, Resource.class);

        Link link = result.getLink(AddressRESTClient.ADDRESSES_REL);
        System.out.println("Following: " + link.getHref());

        // Follow link relation for addresses to access those
        AddressPagedResources addresses = restOperations.getForObject(link.getHref(), AddressPagedResources.class);

        for (AddressResource resource : addresses) {
            Address address = resource.getContent();
            System.out.println(address);
        }
    }

    private static class AddressResource extends Resource<Address> {

    }

    private static class AddressPagedResources extends PagedResources<AddressResource> {

    }
}
