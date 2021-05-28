package com.shu.cms.service.impl;

import com.shu.cms.entity.AdminEntity;
import com.shu.cms.entity.TokenEntity;
import com.shu.cms.mapper.AdminMapper;
import com.shu.cms.mapper.TokenMapper;
import com.shu.cms.model.LoginFormModel;
import com.shu.cms.model.LoginValidateResponseModel;
import com.shu.cms.service.LoginService;
import com.shu.cms.service.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    AdminMapper adminMapper;

    @Autowired
    TokenMapper tokenMapper;

    @Autowired
    TokenService tokenService;

    @Override
    public LoginValidateResponseModel Validate(LoginFormModel loginModel) {
        LoginValidateResponseModel loginValidateResponseModel = new LoginValidateResponseModel();
        switch (loginModel.getRole()) {
            case "admin":

                if (adminMapper.selectEntityById(loginModel.getId()) == null) {
                    loginValidateResponseModel.setFlag(false);
                }

                else {
                    AdminEntity adminEntity = adminMapper.selectEntityById(loginModel.getId());
                    if (loginModel.encryptedPw().equals(adminEntity.getPw())) {
                        // create token by session
                        String token = tokenService.TokenCreate(loginModel.getId(), loginModel.getRole());
                        // set token
                        TokenEntity tokenEntity = new TokenEntity();
                        // record token and others
                        tokenEntity.setToken(token);
                        tokenEntity.setId(adminEntity.getId());
                        tokenEntity.setRole(loginModel.getRole());
                        tokenMapper.insertEntity(tokenEntity);

                        loginValidateResponseModel.setFlag(true);
                        loginValidateResponseModel.setId(tokenEntity.getId());
                        loginValidateResponseModel.setName(adminEntity.getName());
                        loginValidateResponseModel.setRole(tokenEntity.getRole());
                        loginValidateResponseModel.setToken(tokenEntity.getToken());
                    } else
                        loginValidateResponseModel.setFlag(false);
                }

                break;
            case "student":
                break;
            case "teacher":
                break;

            default:
                break;
        }
        return loginValidateResponseModel;

    }

    @Override
    public AdminEntity GetUserEntity(LoginFormModel loginModel) {
        return adminMapper.selectEntityById(loginModel.getId());
    }

    @Override
    public boolean test() {

        return false;

    }

}
