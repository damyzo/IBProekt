package com.ib.demo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "AccountTable")
@Data
public class Account {

    @Id
    private String email;

    private String password;

    public Account() {
    }

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
