package com.shu.cms.service;

import com.shu.cms.model.LoginModel;

public interface LoginService {
    boolean ValidateUser(LoginModel loginModel);
}
