package com.example.jpa_h2_demo;

import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class LoadDataBase {
    @Bean
    CommandLineRunner initDataBase(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(
                new Customer(
                    Set.of(
                        new Phone("+1-000-000-0000"),
                        new Phone("+1-000-000-0000")
                    ),
                    Set.of(
                        new Address("72 Cross Street Fair Lawn"),
                        new Address("9879 Mulberry Street Phoenix")
                    ),
                    "John Doe"
                )
            );

            customerRepository.save(
                new Customer(
                    Set.of(
                        new Phone("+1-000-000-0000"),
                        new Phone("+1-000-000-0000")
                    ),
                    Set.of(
                        new Address("8633 Beechwood Drive Miami"),
                        new Address("923 Jefferson Court Andover")
                    ),
                    "Jane Doe"
                )
            );
        };
    }
}