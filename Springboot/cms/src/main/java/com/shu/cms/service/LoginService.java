package com.shu.cms.service;

import com.shu.cms.entity.AdminEntity;
import com.shu.cms.model.LoginFormModel;
import com.shu.cms.model.LoginValidateResponseModel;

public interface LoginService {
    LoginValidateResponseModel Validate(LoginFormModel loginModel);

    AdminEntity GetUserEntity(LoginFormModel loginModel);

    boolean test();
}
