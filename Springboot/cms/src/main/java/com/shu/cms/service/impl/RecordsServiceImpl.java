package com.shu.cms.service.impl;

import java.util.List;

import com.shu.cms.entity.RecordsEntity;
import com.shu.cms.entity.RecordsEntityEx;
import com.shu.cms.mapper.RecordsMapper;
import com.shu.cms.service.RecordsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordsServiceImpl implements RecordsService {

    @Autowired
    RecordsMapper mapper;

    @Override
    public RecordsEntity getRowById(String id) {
        return mapper.selectEntityById(id);
    }

    @Override
    public List<RecordsEntity> getRows() {
        return mapper.selectEntity();
    }

    @Override
    public List<RecordsEntityEx> getRowsWithName() {
        return mapper.selectEntityWithName();
    }

    @Override
    public int insertRow(RecordsEntity entity) {
        // entity.setPw(encryptService.encrypt(entity.getPw()));
        return mapper.insertEntity(entity);

    }

    @Override
    public int updateRow(RecordsEntity entity) {
        // entity.setPw(encryptService.encrypt(entity.getPw()));
        return mapper.updateEntity(entity);
    }

    @Override
    public int deleteRow(String id) {
        return mapper.deleteEntityById(id);
    }
}
