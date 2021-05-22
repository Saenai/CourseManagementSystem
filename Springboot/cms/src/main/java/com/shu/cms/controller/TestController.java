package com.shu.cms.controller;

import com.shu.cms.entity.AdminEntity;
import com.shu.cms.service.TestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestService testService;

    @RequestMapping("/")
    public String test() {
        return "just a string to test, which means \"Hello World!\"";
    }

    @GetMapping("/getName/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String returnAdminName(@PathVariable String id) {
        return testService.getAdminName(id);
    }

    @GetMapping(value = "/getAdminEntity/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AdminEntity returnAdminEntity(@PathVariable String id) {
        return testService.getAdminEntity(id);
    }

}