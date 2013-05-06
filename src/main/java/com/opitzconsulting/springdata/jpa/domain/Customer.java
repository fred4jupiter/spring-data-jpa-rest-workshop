package com.opitzconsulting.springdata.jpa.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.LocalDate;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "CREATED_AT")
    private Date createdAt;

    @Column(name = "SALES_AMOUNT")
    private Double salesAmount;

    @Column(name = "BIRTHDAY")
    private Date birthday;

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


    public Customer(Double salesAmount, Date birthday, String firstname, String lastname) {
        Assert.hasText(firstname);
        Assert.hasText(lastname);
        Assert.notNull(birthday);
        Assert.notNull(salesAmount);
        this.salesAmount = salesAmount;
        this.birthday = birthday;
        this.firstname = firstname;
        this.lastname = lastname;
        this.createdAt = new LocalDate().toDate();
    }

    public Customer(Double salesAmount, String firstname, String lastname, String email) {
        this(salesAmount, new LocalDate().toDate(), firstname, lastname);
        this.emailAddress = new EmailAddress(email);
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Double getSalesAmount() {
        return salesAmount;
    }

    public Date getBirthday() {
        return birthday;
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
        final ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
        builder.append("firstname", firstname);
        builder.append("lastname", lastname);
        builder.append("emailAddress", emailAddress);
        builder.append("birthday", birthday);
        builder.append("createdAt", createdAt);
        return builder.toString();
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }
}
