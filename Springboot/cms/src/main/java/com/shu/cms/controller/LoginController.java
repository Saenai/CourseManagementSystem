package com.shu.cms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shu.cms.model.LoginFormModel;
import com.shu.cms.model.LoginValidateResponseModel;
import com.shu.cms.service.LoginService;
import com.shu.cms.service.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    TokenService tokenService;

    @PostMapping(value = "/validate")
    @ResponseStatus(HttpStatus.OK)
    public LoginValidateResponseModel validate(@RequestBody LoginFormModel loginModel) {
        return loginService.Validate(loginModel);
    }

    // Test fetch requestbody.headers
    @GetMapping(value = "/test")
    @ResponseStatus(HttpStatus.OK)
    public boolean test(@RequestHeader("Authorization") String token) {
        System.out.println("********************" + token);
        System.out.println(tokenService.TokenVerify(token));
        return tokenService.TokenVerify(token);
    }
}
