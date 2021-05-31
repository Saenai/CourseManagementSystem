package com.shu.cms.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import com.shu.cms.service.EncryptService;

import org.springframework.stereotype.Service;

@Service
public class EncryptServiceImpl implements EncryptService {
    Base64 base64;
    Base64.Decoder decoder = Base64.getDecoder();
    Base64.Encoder encoder = Base64.getEncoder();

    public String encrypt(String string) {
        return encoder.encodeToString(string.getBytes());
    };

    public String decrypt(String string) {
        return new String(decoder.decode(string));
    };
}
