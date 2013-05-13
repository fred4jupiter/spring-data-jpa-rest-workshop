package com.opitzconsulting.springdata.jpa.domain;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "FIRST_NAME")
    @NotNull
    private String firstname;

    @Column(name = "LAST_NAME")
    @NotNull
    private String lastname;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "CUSTOMER_ID")
    private Set<Address> addresses = new HashSet<>();

    protected Customer() {
        // for hibernate
    }

    public Customer(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
        builder.append("firstname", firstname);
        builder.append("lastname", lastname);
        return builder.toString();
    }

    public void addAddress(Address address) {
        address.setCustomer(this);
        this.addresses.add(address);
    }
}
