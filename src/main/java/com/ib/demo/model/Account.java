package com.ib.demo.model;

import lombok.Data;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity(name = "AccountTable")
@Data
public class Account {

    @Id
    private String email;

    private String password;

    private String accessKey;

    private SecretKey secretKey;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Account() {
    }

    public Account(String email, String password, SecretKey accessKey, SecretKey secretKey, Role role) {
        this.email = email;
        this.password = password;
        this.accessKey = Base64.encodeBase64String(accessKey.getEncoded());
        this.secretKey = secretKey;
        this.role = role;
    }

    public String getSecretKeyEncoded() {
        return Base64.encodeBase64String(secretKey.getEncoded());
    }

    public String getAccessKeyEncoded() {
        return accessKey;
    }
}
