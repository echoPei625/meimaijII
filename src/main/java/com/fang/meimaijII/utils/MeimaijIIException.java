package com.fang.meimaijII.utils;

public class MeimaijIIException extends RuntimeException{

    private static final long serialVersionUID = 8201649741223629035L;

    private int responseCode;

    public MeimaijIIException(String message, int responseCode){
        super(message);
        this.responseCode = responseCode;
    }

    public MeimaijIIException(String message, Throwable cause, int responseCode){
        super(message, cause);
        this.responseCode = responseCode;
    }

    public int getResponseCode(){
        return responseCode;
    }
}
