package com.shu.cms.service.impl;

import com.shu.cms.entity.AdminEntity;
import com.shu.cms.mapper.AdminMapper;
import com.shu.cms.service.TestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    AdminEntity adminEntity;

    @Autowired
    AdminMapper testMapper;

    @Override
    public String getAdminName(String id) {
        return testMapper.selectEntityById(id).getName();
    }

    @Override
    public AdminEntity getAdminEntity(String id) {
        return testMapper.selectEntityById(id);
    }

}
