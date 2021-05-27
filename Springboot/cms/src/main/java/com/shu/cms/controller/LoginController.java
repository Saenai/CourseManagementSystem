package com.shu.cms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

import com.shu.cms.entity.AdminEntity;
import com.shu.cms.model.LoginModel;
import com.shu.cms.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping(value = "/validate")
    @ResponseStatus(HttpStatus.OK)
    public boolean validate(@RequestBody LoginModel loginModel) {
        return loginService.ValidateUser(loginModel);
    }

    @PostMapping(value = "/getuser")
    public AdminEntity getUser(@RequestBody LoginModel loginModel) {
        return loginService.GetUserEntity(loginModel);
    }

}
