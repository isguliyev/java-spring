package com.example.jpa_h2_demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(
        """
        SELECT DISTINCT customer FROM Customer customer
        JOIN FETCH customer.addresses
        JOIN FETCH customer.phones
        """
    )
    public Set<Customer> findCustomersWithAddressesAndPhones();
}