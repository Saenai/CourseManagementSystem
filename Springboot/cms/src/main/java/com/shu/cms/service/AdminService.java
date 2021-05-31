package com.shu.cms.service;

import java.util.List;

import com.shu.cms.entity.AdminEntity;

public interface AdminService {
    List<AdminEntity> getAdmins();

    int insertAdmin(AdminEntity adminEntity);
}
