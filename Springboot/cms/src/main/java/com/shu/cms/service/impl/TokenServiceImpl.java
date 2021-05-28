package com.shu.cms.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.shu.cms.config.GlobalVariable;
import com.shu.cms.service.TokenService;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class TokenServiceImpl implements TokenService {
    public String TokenCreate(String id, String role) {
        String token = "";
        try {
            // Token will be available in 1 h.
            Date start = new Date(System.currentTimeMillis());
            Date end = new Date(System.currentTimeMillis() + 60 * 60 * 1000);
            // 秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(GlobalVariable.TOKEN_KEY);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            // 携带username，password信息，生成签名
            token = JWT.create().withHeader(header).withClaim("id", id).withClaim("role", role).withIssuedAt(start)
                    .withExpiresAt(end).sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return token;

    }

    public boolean TokenVerify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(GlobalVariable.TOKEN_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Map<String, Claim> tokenVerify(String token) {
        DecodedJWT jwt = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(GlobalVariable.TOKEN_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jwt.getClaims();
    }

    public Long getId(String token) {
        Map<String, Claim> claims = tokenVerify(token);
        Claim id_claim = claims.get("id");
        if (null == id_claim || ObjectUtils.isEmpty(id_claim.asString())) {
            // token 校验失败, 抛出Token验证非法异常
        }
        return Long.valueOf(id_claim.asString());
    }

    public Long getRole(String token) {
        Map<String, Claim> claims = tokenVerify(token);
        Claim role_claim = claims.get("role");
        if (null == role_claim || ObjectUtils.isEmpty(role_claim.asString())) {
            // token 校验失败, 抛出Token验证非法异常
        }
        return Long.valueOf(role_claim.asString());
    }
}
