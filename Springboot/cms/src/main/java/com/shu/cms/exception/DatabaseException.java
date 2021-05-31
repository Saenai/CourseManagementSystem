package com.shu.cms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "用户信息错误")
public class DatabaseException extends Exception {

    public DatabaseException() {
        // super(msg);
    }
}
