package com.example.MegaSenaAPI.account;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;

    public Account() {}

    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Account(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format(
            "%s [id=%d, name=%s, password=%s]",
            this.getClass().getSimpleName(),
            this.id,
            this.name,
            this.password
        );
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Account account)) {
            return false;
        }

        return this.id != null && this.id.equals(account.id);
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}