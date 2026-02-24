package com.example.jpa_h2_demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.http.HttpStatus;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {
    private final CustomerRepository customerRepository;
    private final CustomerModelAssembler customerModelAssembler;

    public CustomerController(
        CustomerRepository customerRepository,
        CustomerModelAssembler customerModelAssembler
    ) {
        this.customerRepository = customerRepository;
        this.customerModelAssembler = customerModelAssembler;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<Customer> addCustomer(@RequestBody Customer customer) {
        return this.customerModelAssembler.toModel(this.customerRepository.save(customer));
    }

    @GetMapping
    public CollectionModel<EntityModel<Customer>> findAllCustomers(Pageable pageable) {
        return CollectionModel.of(
            this.customerModelAssembler.toCollectionModel(this.customerRepository.findAll(pageable)),
            linkTo(methodOn(CustomerController.class).findAllCustomers(pageable)).withSelfRel()
        );
    }

    @GetMapping(path = "/{id}")
    public EntityModel<Customer> findCustomerById(@PathVariable("id") Long id) {
        return this.customerModelAssembler.toModel(
            this.customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException(id)
            )
        );
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCustomerById(@PathVariable("id") Long id) {
        if (!this.customerRepository.existsById(id)) {
            throw new CustomerNotFoundException(id);
        }

        this.customerRepository.deleteById(id);
    }

    @PutMapping(path = "/{id}")
    public EntityModel<Customer> updateCustomerById(
        @PathVariable("id") Long id,
        @RequestBody Customer customer
    ) {
        Customer customerToUpdate = this.customerRepository.findById(id).orElseThrow(
            () -> new CustomerNotFoundException(id)
        );

        customerToUpdate.setPhones(customer.getPhones());
        customerToUpdate.setAddresses(customer.getAddresses());
        customerToUpdate.setData(customer.getData());

        return this.customerModelAssembler.toModel(this.customerRepository.save(customerToUpdate));
    }
}