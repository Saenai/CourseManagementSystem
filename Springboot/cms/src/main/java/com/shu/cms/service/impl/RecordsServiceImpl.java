package com.shu.cms.service.impl;

import com.shu.cms.entity.RecordsEntity;
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

}
