package com.opitzconsulting.springdata.jpa.repository;


import com.opitzconsulting.springdata.jpa.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.repository.annotation.RestResource;

@RestResource(rel = "addresses", path = "addresses")
public interface AddressRepository extends JpaRepository<Address, Long> {
}
