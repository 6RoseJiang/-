package com.example.server.vo;

import com.example.server.enums.ResultEnum;

public class Result<T> {
    public int code;
    public String msg;
    public T data;

    public static <T> Result success(){
        Result r = new Result(ResultEnum.SUCCESS.getMsg(), ResultEnum.SUCCESS.getCode(), null);
        return r;
    }

    public static <T> Result success(T data){
        Result r = new Result(ResultEnum.SUCCESS.getMsg(), ResultEnum.SUCCESS.getCode(), data);
        return r;
    }

    public static <T> Result fail(){
        Result r = new Result(ResultEnum.ERROR_UNKNOWN.getMsg(), ResultEnum.ERROR_UNKNOWN.getCode(), null);
        return r;
    }

    public static <T> Result fail(ResultEnum rEnum){
        Result r = new Result(rEnum.getMsg(), rEnum.getCode(), null);
        return r;
    }

    private Result(String msg, int code, T data){
        this.msg = msg;
        this.code = code;
        this.data = data;
    }
}
