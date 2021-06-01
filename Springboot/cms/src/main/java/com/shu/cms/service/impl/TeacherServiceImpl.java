package com.shu.cms.service.impl;

import java.util.List;

import com.shu.cms.entity.TeacherEntity;
import com.shu.cms.mapper.TeacherMapper;
import com.shu.cms.service.EncryptService;
import com.shu.cms.service.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherMapper mapper;
    @Autowired
    EncryptService encryptService;

    @Override
    public List<TeacherEntity> getRows() {
        return mapper.selectEntity();
    }

    @Override
    public int insertRow(TeacherEntity entity) {
        // entity.setPw(encryptService.encrypt(entity.getPw()));
        return mapper.insertEntity(entity);

    }

    @Override
    public int updateRow(TeacherEntity entity) {
        // entity.setPw(encryptService.encrypt(entity.getPw()));
        return mapper.updateEntity(entity);
    }

    @Override
    public int deleteRow(String id) {
        return mapper.deleteEntityById(id);
    }
}
