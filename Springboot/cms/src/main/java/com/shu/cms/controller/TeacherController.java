package com.shu.cms.controller;

import com.shu.cms.entity.RecordsEntity;
import com.shu.cms.exception.DatabaseException;
import com.shu.cms.service.RecordsService;
import com.shu.cms.service.TeacherService;
import com.shu.cms.service.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    @Autowired
    TokenService tokenService;

    @Autowired
    RecordsService recordsService;
    @Autowired
    TeacherService teacherService;

    @PostMapping("/courseinfo")
    @ResponseStatus(HttpStatus.OK)
    RecordsEntity GetRecords(@RequestHeader("Authorization") String token, @RequestBody String id)
            throws DatabaseException {
        if (tokenService.TokenVerify(token) == true) {
            // TODO
            return recordsService.getRowById(id);
        } else {
            throw new DatabaseException();
        }
    }
}
