package com.shu.cms.service;

import java.util.Map;

import com.auth0.jwt.interfaces.Claim;

public interface TokenService {
    String TokenCreate(String id, String role);

    boolean TokenVerify(String token);

    Map<String, Claim> tokenVerify(String token);

    Long getId(String token);

    Long getRole(String token);
}
