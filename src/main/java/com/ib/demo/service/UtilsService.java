package com.ib.demo.service;

import javax.crypto.SecretKey;

public interface UtilsService {
    SecretKey generateSecretKey();
    SecretKey generateAccessKey();
    String signature(SecretKey secretKey, String stringToSign);
}
