package com.opitzconsulting.springdata.jpa.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "SALES_AMOUNT")
    private Double salesAmount;

    @Column(name = "FIRST_NAME")
    private String firstname;

    @Column(name = "LAST_NAME")
    private String lastname;

    @Column(name = "EMAIL", unique = true)
    private EmailAddress emailAddress;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "CUSTOMER_ID")
    private Set<Address> addresses = new HashSet<>();

    protected Customer() {
        // for hibernate
    }

    public Customer(Double salesAmount, String firstname, String lastname, String emailAddress) {
        this.salesAmount = salesAmount;
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailAddress = new EmailAddress(emailAddress);
    }

    public Double getSalesAmount() {
        return salesAmount;
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

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
        builder.append("firstname", firstname);
        builder.append("lastname", lastname);
        builder.append("emailAddress", emailAddress);
        builder.append("addresses", addresses);
        return builder.toString();
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }
}
