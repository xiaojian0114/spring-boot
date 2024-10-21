package org.example.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor


public enum ErrorCode {

    UNAUTHORIZED(401,"还未授权，不能访问"),
    FORBIDDEN(403,"没有权限，禁止访问"),
    INTERNAL_SERVER_ERROR(500,"服务器异常，请稍后再试"),

    OPERATION_FAIL(3000,"操作失败"),
    LOGIN_STATUS_EXPIRE(3001,"登录过期"),
    CODE_SEND_FAIL(3002,"短信发送失败"),
    PARAMS_ERROR(3003,"参数异常"),
    SMS_CODE_ERROR(3004,"短信验证码错误"),
    ACCOUNT_DISABLED(3005,"账号被禁用"),
    USER_NOT_EXIST(3006,"用户不存在");

    private final int code;
    private final String msg;

}
