package com.opitzconsulting.springdata.jpa.repository;

import com.opitzconsulting.springdata.jpa.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
