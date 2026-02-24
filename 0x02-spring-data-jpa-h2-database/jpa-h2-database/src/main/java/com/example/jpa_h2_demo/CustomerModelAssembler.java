package com.example.jpa_h2_demo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Component;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

@Component
public class CustomerModelAssembler implements RepresentationModelAssembler<Customer, EntityModel<Customer>> {
    @Override
    public EntityModel<Customer> toModel(Customer customer) {
        return EntityModel.of(
            customer,
            linkTo(methodOn(CustomerController.class).findCustomerById(customer.getId())).withSelfRel(),
            linkTo(methodOn(CustomerController.class).findAllCustomers(null)).withRel("customers")
        );
    }
}