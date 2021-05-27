package com.shu.cms.service.impl;

import com.shu.cms.entity.AdminEntity;
import com.shu.cms.mapper.AdminMapper;
import com.shu.cms.model.LoginModel;
import com.shu.cms.service.LoginService;

import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public boolean ValidateUser(LoginModel loginModel) {
        switch (loginModel.getRole()) {
            case "admin":

                if (adminMapper.selectEntityById(loginModel.getId()) == null)
                    return false;

                else if (loginModel.encryptedPw().equals(adminMapper.selectEntityById(loginModel.getId()).getPw()))
                    return true;

                break;

            default:
                break;
        }
        return false;

    }

    @Override
    public AdminEntity GetUserEntity(LoginModel loginModel) {
        return adminMapper.selectEntityById(loginModel.getId());
    }

}
