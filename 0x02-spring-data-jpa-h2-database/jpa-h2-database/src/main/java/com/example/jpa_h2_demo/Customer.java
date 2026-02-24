package com.example.jpa_h2_demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;

import java.util.Set;
import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonManagedReference
    @OneToMany(
        mappedBy = "customer",
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private Set<Phone> phones;
    @JsonManagedReference
    @OneToMany(
        mappedBy = "customer",
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private Set<Address> addresses;
    private String data;

    public Customer() {
        this.phones = new HashSet<Phone>();
        this.addresses = new HashSet<Address>();
    }

    public Customer(Set<Phone> phones, Set<Address> addresses, String data) {
        this.setPhones(phones);
        this.setAddresses(addresses);
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format(
            "%s [id=%d, data=%s]",
            this.getClass().getSimpleName(),
            this.id,
            this.data
        );
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof Customer)) {
            return false;
        }

        Customer customer = (Customer) object;

        return this.id != null && this.id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Long getId() {
        return this.id;
    }

    public Set<Phone> getPhones() {
        return this.phones;
    }

    public Set<Address> getAddresses() {
        return this.addresses;
    }

    public String getData() {
        return this.data;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPhones(Set<Phone> phones) {
        if (this.phones == null) {
            this.phones = new HashSet<Phone>();
        } else {
            this.phones.clear();
        }

        if (phones == null) {
            return;
        }

        for (Phone phone : phones) {
            this.phones.add(phone);
            phone.setCustomer(this);
        }
    }

    public void setAddresses(Set<Address> addresses) {
        if (this.addresses == null) {
            this.addresses = new HashSet<Address>();
        } else {
            this.addresses.clear();
        }

        if (addresses == null) {
            return;
        }

        for (Address address : addresses) {
            this.addresses.add(address);
            address.setCustomer(this);
        }
    }

    public void setData(String data) {
        this.data = data;
    }
}