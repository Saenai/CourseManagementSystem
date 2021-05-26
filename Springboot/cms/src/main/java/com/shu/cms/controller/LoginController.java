package com.shu.cms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shu.cms.model.LoginModel;
import com.shu.cms.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping(value = "/validate")
    @ResponseStatus(HttpStatus.OK)
    public boolean getMethodName(@RequestBody LoginModel loginModel) {
        return loginService.ValidateUser(loginModel);
    }

}
