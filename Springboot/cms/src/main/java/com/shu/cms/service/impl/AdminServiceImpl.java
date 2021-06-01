package com.shu.cms.service.impl;

import java.util.List;

import com.shu.cms.entity.AdminEntity;
import com.shu.cms.mapper.AdminMapper;
import com.shu.cms.service.AdminService;
import com.shu.cms.service.EncryptService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    EncryptService encryptService;

    @Override
    public List<AdminEntity> getAdmins() {
        List<AdminEntity> res = adminMapper.selectEntity();
        for (AdminEntity it : res) {
            if (!it.getId().equals("zs") && !it.getId().equals("ls") && !it.getId().equals("ww")
                    && !it.getId().equals("admin")) {
                it.setPw(encryptService.decrypt(it.getPw()));
            }
        }
        return res;
    }

    @Override
    public int insertAdmin(AdminEntity adminEntity) {
        adminEntity.setPw(encryptService.encrypt(adminEntity.getPw()));
        return adminMapper.insertEntity(adminEntity);

    }

    @Override
    public int updateAdmin(AdminEntity adminEntity) {
        adminEntity.setPw(encryptService.encrypt(adminEntity.getPw()));
        return adminMapper.updateEntity(adminEntity);
    }

    @Override
    public int deleteAdmin(String id) {
        System.out.println("**********************" + id);
        return adminMapper.deleteEntityById(id);
    }

}
