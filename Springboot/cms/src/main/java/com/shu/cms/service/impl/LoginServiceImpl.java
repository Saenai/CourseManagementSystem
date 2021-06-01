package com.shu.cms.service.impl;

import com.shu.cms.entity.AdminEntity;
import com.shu.cms.entity.StudentEntity;
import com.shu.cms.entity.TeacherEntity;
import com.shu.cms.entity.TokenEntity;
import com.shu.cms.mapper.AdminMapper;
import com.shu.cms.mapper.StudentMapper;
import com.shu.cms.mapper.TeacherMapper;
import com.shu.cms.mapper.TokenMapper;
import com.shu.cms.model.LoginFormModel;
import com.shu.cms.model.LoginValidateResponseModel;
import com.shu.cms.service.EncryptService;
import com.shu.cms.service.LoginService;
import com.shu.cms.service.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    AdminMapper adminMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    TokenMapper tokenMapper;

    @Autowired
    TokenService tokenService;

    @Autowired
    EncryptService encryptService;

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
                    String password = "";
                    // zs, ls, ww作为测试用用户不参与密码解密的过程
                    if (adminEntity.getId().equals("zs") || adminEntity.getId().equals("ls")
                            || adminEntity.getId().equals("ww")) {
                        password = adminEntity.getPw();
                    } else
                        password = encryptService.decrypt(adminEntity.getPw());
                    if (loginModel.getPw().equals(password)) {
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
            case "teacher":
                if (teacherMapper.selectEntityById(loginModel.getId()) == null) {
                    loginValidateResponseModel.setFlag(false);
                } else {
                    TeacherEntity teacherEntity = teacherMapper.selectEntityById(loginModel.getId());
                    String password = "";
                    password = teacherEntity.getPw();
                    if (loginModel.getPw().equals(password)) {
                        // create token by session
                        String token = tokenService.TokenCreate(loginModel.getId(), loginModel.getRole());
                        // set token
                        TokenEntity tokenEntity = new TokenEntity();
                        // record token and others
                        tokenEntity.setToken(token);
                        tokenEntity.setId(teacherEntity.getId());
                        tokenEntity.setRole(loginModel.getRole());
                        tokenMapper.insertEntity(tokenEntity);

                        loginValidateResponseModel.setFlag(true);
                        loginValidateResponseModel.setId(tokenEntity.getId());
                        loginValidateResponseModel.setName(teacherEntity.getName());
                        loginValidateResponseModel.setRole(tokenEntity.getRole());
                        loginValidateResponseModel.setToken(tokenEntity.getToken());
                    } else
                        loginValidateResponseModel.setFlag(false);
                }
                break;
            case "student":
                if (studentMapper.selectEntityById(loginModel.getId()) == null) {
                    loginValidateResponseModel.setFlag(false);
                } else {
                    StudentEntity studentEntity = studentMapper.selectEntityById(loginModel.getId());
                    String password = "";
                    password = studentEntity.getPw();
                    if (loginModel.getPw().equals(password)) {
                        // create token by session
                        String token = tokenService.TokenCreate(loginModel.getId(), loginModel.getRole());
                        // set token
                        TokenEntity tokenEntity = new TokenEntity();
                        // record token and others
                        tokenEntity.setToken(token);
                        tokenEntity.setId(studentEntity.getId());
                        tokenEntity.setRole(loginModel.getRole());
                        tokenMapper.insertEntity(tokenEntity);

                        loginValidateResponseModel.setFlag(true);
                        loginValidateResponseModel.setId(tokenEntity.getId());
                        loginValidateResponseModel.setName(studentEntity.getName());
                        loginValidateResponseModel.setRole(tokenEntity.getRole());
                        loginValidateResponseModel.setToken(tokenEntity.getToken());
                    } else
                        loginValidateResponseModel.setFlag(false);
                }
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
