package com.shu.cms.service.impl;

import java.util.List;

import com.shu.cms.entity.StudentEntity;
import com.shu.cms.mapper.StudentMapper;
import com.shu.cms.service.EncryptService;
import com.shu.cms.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper mapper;
    @Autowired
    EncryptService encryptService;

    @Override
    public List<StudentEntity> getRows() {
        return mapper.selectEntity();
    }

    @Override
    public int insertRow(StudentEntity entity) {
        // entity.setPw(encryptService.encrypt(entity.getPw()));
        return mapper.insertEntity(entity);

    }

    @Override
    public int updateRow(StudentEntity entity) {
        // entity.setPw(encryptService.encrypt(entity.getPw()));
        return mapper.updateEntity(entity);
    }

    @Override
    public int deleteRow(String id) {
        return mapper.deleteEntityById(id);
    }
}
