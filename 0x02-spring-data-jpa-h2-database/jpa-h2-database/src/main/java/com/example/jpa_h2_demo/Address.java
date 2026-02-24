package com.example.jpa_h2_demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Id;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private Customer customer;
    private String data;

    public Address() {}

    public Address(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format(
            "%s [id=%d, customer=%s, data=%s]",
            this.getClass().getSimpleName(),
            this.id,
            this.customer,
            this.data
        );
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof Address)) {
            return false;
        }

        Address address = (Address) object;

        return this.id != null && this.id.equals(address.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Long getId() {
        return this.id;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public String getData() {
        return this.data;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setData(String data) {
        this.data = data;
    }
}