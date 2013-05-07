package com.opitzconsulting.springdata.jpa.repository;

import com.opitzconsulting.springdata.jpa.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.repository.annotation.RestResource;

@RestResource(rel = "customers", path = "customers")
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
