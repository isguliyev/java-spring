package com.example.MegaSenaAPI.account;

import com.example.MegaSenaAPI.account.dto.AccountResponse;

import jakarta.validation.constraints.NotNull;

import org.jspecify.annotations.Nullable;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AccountPrincipals implements UserDetails {
    private final Account account;

    public AccountPrincipals(Account account) {
        this.account = account;
    }

    @Override
    public @NotNull Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public @Nullable String getPassword() {
        return this.account.getPassword();
    }

    @Override
    public @NotNull String getUsername() {
        return this.account.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}