package com.shu.cms.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import com.shu.cms.entity.FileEntity;
import com.shu.cms.entity.RecordsEntity;
import com.shu.cms.entity.RecordsEntityEx;
import com.shu.cms.entity.StudentEntity;
import com.shu.cms.exception.DatabaseException;
import com.shu.cms.service.FileService;
import com.shu.cms.service.RecordsService;
import com.shu.cms.service.StudentService;
import com.shu.cms.service.TeacherService;
import com.shu.cms.service.TokenService;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    @Autowired
    TokenService tokenService;

    @Autowired
    RecordsService recordsService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;

    @Autowired
    FileService fileService;

    @GetMapping("/courseinfo")
    @ResponseStatus(HttpStatus.OK)
    List<StudentEntity> GetRecords(@RequestHeader("Authorization") String token) throws DatabaseException {
        if (tokenService.TokenVerify(token) == true) {
            return studentService.getRowsOfIdName();
        } else {
            throw new DatabaseException();
        }
    }

    @GetMapping("/classmanage")
    @ResponseStatus(HttpStatus.OK)
    public List<RecordsEntityEx> GetRecordsWithName(@RequestHeader("Authorization") String token)
            throws DatabaseException {
        if (tokenService.TokenVerify(token) == true) {
            return recordsService.getRowsWithName();
        } else {
            throw new DatabaseException();
        }
    };

    @PostMapping("/classmanage")
    @ResponseStatus(HttpStatus.OK)
    public int PostRecordss(@RequestHeader("Authorization") String token, @RequestBody RecordsEntity entity)
            throws DatabaseException {
        if (tokenService.TokenVerify(token) == true) {
            return recordsService.insertRow(entity);
        } else {
            throw new DatabaseException();
        }
    };

    @PutMapping("/classmanage")
    @ResponseStatus(HttpStatus.OK)
    public int PutRecordss(@RequestHeader("Authorization") String token, @RequestBody RecordsEntity entity)
            throws DatabaseException {
        if (tokenService.TokenVerify(token) == true) {
            return recordsService.updateRow(entity);
        } else {
            throw new DatabaseException();
        }
    };

    @PostMapping("/classmanage/delete")
    @ResponseStatus(HttpStatus.OK)
    public int DeleteRecordss(@RequestHeader("Authorization") String token, @RequestBody String id)
            throws DatabaseException {
        if (tokenService.TokenVerify(token) == true) {
            return recordsService.deleteRow(id);
        } else {
            throw new DatabaseException();
        }
    };

    @PostMapping("/fileupload/up")
    @ResponseStatus(HttpStatus.OK)
    public int FileUpload(@RequestBody MultipartFile file) throws DatabaseException {
        try {
            return fileService.SaveFile(file);
        } catch (Exception e) {
            return 0;
        }
    }

    @GetMapping("/fileupload")
    @ResponseStatus(HttpStatus.OK)
    List<FileEntity> GetFile(@RequestHeader("Authorization") String token) throws DatabaseException {
        if (tokenService.TokenVerify(token) == true) {
            return fileService.GetFileInfo();
        } else {
            throw new DatabaseException();
        }
    }

    @PostMapping(value = "/fileupload/down", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public byte[] FileDownload(@RequestHeader("Authorization") String token, @RequestBody String filename)
            throws IOException {
        File dest = fileService.GetFile(filename);
        FileInputStream inputStream = new FileInputStream(dest);
        return IOUtils.toByteArray(inputStream);
    }

    @PostMapping("/fileupload/del")
    @ResponseStatus(HttpStatus.OK)
    public int FileDelete(@RequestHeader("Authorization") String token, @RequestBody String filename) {
        return fileService.DeleteFile(filename);
    }
}
