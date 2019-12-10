package com.ibm.fullstack.common;

public enum ResultCode implements IErrorCode{
    SUCCESS(200, "success"),
    FAILED(500, "fail"),
    VALIDATE_FAILED(404, "page not found"),
    UNAUTHORIZED(401, "not login or token expired"),
    FORBIDDEN(403, "access denied");
    private long code;
    private String msg;

    private ResultCode(long code, String msg){
        this.code = code;
        this.msg = msg;
    }

    @Override
    public long getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
