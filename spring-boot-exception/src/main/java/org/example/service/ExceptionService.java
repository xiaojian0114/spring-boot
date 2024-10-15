package org.example.service;

import org.example.exception.ServerException;
import org.springframework.stereotype.Service;

@Service
public class ExceptionService {

    public void unAuthorizedError() {
        throw new ServerException("没有登录");
    }

    public void systemError() {
        throw new ServerException("系统异常");
    }
}
