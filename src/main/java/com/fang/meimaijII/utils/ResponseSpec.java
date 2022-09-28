package com.fang.meimaijII.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseSpec{

    private int code;

    private String message;

    private Object content;

    public ResponseSpec(int code, String message){
        super();
        this.code = code;
        this.message = message;
    }
}
