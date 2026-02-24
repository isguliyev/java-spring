package com.example.MegaSenaAPI.account;

import com.example.MegaSenaAPI.account.dto.AccountResponse;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AccountModelAssembler implements RepresentationModelAssembler<AccountResponse, EntityModel<AccountResponse>> {
    @Override
    public EntityModel<AccountResponse> toModel(AccountResponse accountResponse) {
        return EntityModel.of(
            accountResponse,
            linkTo(methodOn(AccountController.class).findAccountById(accountResponse.id())).withSelfRel(),
            linkTo(methodOn(AccountController.class).findAllAccounts()).withRel("accounts")
        );
    }
}
