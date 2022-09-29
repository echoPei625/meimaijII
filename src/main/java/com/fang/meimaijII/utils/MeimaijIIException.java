package com.fang.meimaijII.utils;

public class MeimaijIIException extends RuntimeException{

    private static final long serialVersionUID = 8201649741223629035L;

    public MeimaijIIException(String message){
        super(message);
    }

    public MeimaijIIException(String message, Throwable cause){
        super(message, cause);
    }
}
