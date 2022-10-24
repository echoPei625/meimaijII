package com.fang.meimaijII.enums;

public enum ModuleEnum{

    JUST_NEED_VERIFY(0),
    SUPERRR(1),
    GET(2),
    GET_BACK(3),
    EDIT(4),
    DELETE(5);
    

    private int code;

    private ModuleEnum(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
