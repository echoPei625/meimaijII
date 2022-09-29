package com.fang.meimaijII.enums;

public enum ResponseCode{

    SUCCESS(200, "執行成功"),
    INPUT_ISSUE(9000, "傳入參數有誤"),
    LOGIC_ISSUE(9001, "因商業邏輯執行失敗"),
    TOKEN_ISSUE(9002, "token過期"),
    FORBIDAN(9003, "無相關權限");

    private int code;

    private String message;

    private ResponseCode(int code){
        this.code = code;
    }

    private ResponseCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }
}
