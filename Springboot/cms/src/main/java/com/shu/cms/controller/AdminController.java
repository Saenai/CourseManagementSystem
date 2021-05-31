package com.shu.cms.controller;

import java.util.List;

import com.shu.cms.entity.AdminEntity;
import com.shu.cms.exception.DatabaseException;
import com.shu.cms.service.AdminService;
import com.shu.cms.service.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    TokenService tokenService;
    @Autowired
    AdminService adminService;

    @GetMapping("/admins")
    @ResponseStatus(HttpStatus.OK)
    public List<AdminEntity> GetAdmins(@RequestHeader("Authorization") String token) throws DatabaseException {
        if (tokenService.TokenVerify(token) == true) {
            return adminService.getAdmins();
        } else {
            throw new DatabaseException();
        }
    };

    @PostMapping("/admins")
    @ResponseStatus(HttpStatus.OK)
    public int PostAdmins(@RequestHeader("Authorization") String token, @RequestBody AdminEntity adminEntity)
            throws DatabaseException {
        if (tokenService.TokenVerify(token) == true) {
            return adminService.insertAdmin(adminEntity);
        } else {
            throw new DatabaseException();
        }
    };
}
