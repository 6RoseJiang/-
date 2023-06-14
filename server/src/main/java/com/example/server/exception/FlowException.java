package com.example.server.exception;

import com.example.server.enums.ResultEnum;

public class FlowException extends RuntimeException{
    private FlowException(String msg){
        super(msg);
    }

    public static FlowException NotFound(){
        return new FlowException(ResultEnum.ERROR_NOTFOUND.getMsg());
    }

    public static FlowException OperateFail(){
        return new FlowException(ResultEnum.ERROR_OPERATION.getMsg());
    }

    public static FlowException Unknown(){
        return new FlowException(ResultEnum.ERROR_UNKNOWN.getMsg());
    }
}
