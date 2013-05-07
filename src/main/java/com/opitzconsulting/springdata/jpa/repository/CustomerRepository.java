package com.opitzconsulting.springdata.jpa.repository;

import com.opitzconsulting.springdata.jpa.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.repository.annotation.RestResource;

import java.util.List;

@RestResource(rel = "customers", path = "customers")
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Makes the findAll-method pageable. With that you can use pagable request parameters like:
     * <p/>
     * <p><a href="http://localhost:8080/customers?limit=2&page=1">Limit to 2 and view page 1</a></p>
     *
     * @param pageable
     * @return
     */
    Page<Customer> findAll(Pageable pageable);

    /**
     * Example for sorting:
     * <p/>
     * <p><a href="http://localhost:8080/customers/search/lastnameStartsWith?lastname=F&sort=lastname&lastname.dir=asc">Show customers start with F and sort by lastname</a></p>
     *
     * @param lastname
     * @param pageable
     * @return
     */
    @RestResource(path = "lastnameStartsWith", rel = "lastnameStartsWith")
    Page<Customer> findByLastnameStartsWith(@Param("lastname") String lastname, Pageable pageable);

    @RestResource(path = "firstname", rel = "firstname")
    List<Customer> findByFirstname(@Param("firstname") String firstname);
}
