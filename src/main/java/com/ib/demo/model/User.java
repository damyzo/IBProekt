package com.ib.demo.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "UsersTable")
public class User {

    @Id
    @GeneratedValue()
    private Long id;

    private String name;

    private String email;

    private String phone;

    private String country;

    public User() {
    }

    public User(String name, String email, String phone, String country) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.country = country;
    }
}
