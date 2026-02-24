package com.example.MegaSenaAPI.account;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.example.MegaSenaAPI.account.dto.AccountResponse;
import com.example.MegaSenaAPI.account.dto.RegisterRequest;
import com.example.MegaSenaAPI.account.dto.UpdateRequest;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/accounts")
public class AccountController {
    private final AccountService accountService;
    private final AccountModelAssembler accountModelAssembler;

    public AccountController(
        AccountService accountService,
        AccountModelAssembler accountModelAssembler
    ) {
        this.accountService = accountService;
        this.accountModelAssembler = accountModelAssembler;
    }

    @PostMapping(path = "/login")
    public String login(@RequestBody @Valid RegisterRequest registerRequest) {
        return this.accountService.verify(registerRequest);
    }

    @GetMapping
    public CollectionModel<EntityModel<AccountResponse>> findAllAccounts() {
        return CollectionModel.of(
            this.accountModelAssembler.toCollectionModel(this.accountService.findAllAccounts()),
            linkTo(methodOn(AccountController.class).findAllAccounts()).withSelfRel()
        );
    }

    @GetMapping(path = "/{id}")
    public EntityModel<AccountResponse> findAccountById(@PathVariable(name = "id") Long id) {
        return this.accountModelAssembler.toModel(this.accountService.findAccountById(id));
    }

    @PostMapping(path = "/register")
    public EntityModel<AccountResponse> registerAccount(
        @RequestBody @Valid RegisterRequest registerRequest
    ) {
        return this.accountModelAssembler.toModel(
            this.accountService.registerAccount(registerRequest)
        );
    }

    @PutMapping(path = "/{id}")
    public EntityModel<AccountResponse> updateAccountById(
        @RequestBody @Valid UpdateRequest updateRequest,
        @PathVariable(name = "id") Long id
    ) {
        return this.accountModelAssembler.toModel(
            this.accountService.updateAccountById(updateRequest, id)
        );
    }

    @DeleteMapping(path = "/{id}")
    public void deleteAccountById(@PathVariable(name = "id") Long id) {
        this.accountService.deleteAccountById(id);
    }
}