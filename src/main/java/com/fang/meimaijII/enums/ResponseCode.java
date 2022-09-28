package com.fang.meimaijII.enums;

public enum ResponseCode{

    SUCCESS(200),
    INPUT_ISSUE(9000),
    LOGIC_ISSUE(9001),
    TOKEN_ISSUE(9002);

    private int code;

    private ResponseCode(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
