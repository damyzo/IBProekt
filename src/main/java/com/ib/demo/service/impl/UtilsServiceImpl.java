package com.ib.demo.service.impl;

import com.ib.demo.service.UtilsService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class UtilsServiceImpl implements UtilsService {


    private final KeyGenerator keyGenerator;

    public UtilsServiceImpl(KeyGenerator keyGenerator) {
        this.keyGenerator = keyGenerator;
    }

    @Override
    public SecretKey generateSecretKey() {
        keyGenerator.init(240);
        return keyGenerator.generateKey();
    }

    @Override
    public SecretKey generateAccessKey() {
        keyGenerator.init(120);
        return keyGenerator.generateKey();
    }

    @Override
    public String signature(SecretKey secretKey, String stringToSign) {
        try {
            Mac mac = Mac.getInstance("HMACSHA1");
            mac.init(secretKey);
            return Base64.encodeBase64String(mac.doFinal(stringToSign.getBytes()));
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            System.out.println(e.getMessage());
        }
        return "Invalid";
    }


}
