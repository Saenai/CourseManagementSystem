package com.shu.cms.service;

import com.shu.cms.entity.AdminEntity;

public interface TestService {
    public String getAdminName(String id);

    public AdminEntity getAdminEntity(String id);
}
