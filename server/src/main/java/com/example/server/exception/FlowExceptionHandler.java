package com.example.server.exception;

import com.example.server.enums.ResultEnum;
import com.example.server.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@CrossOrigin
@RestControllerAdvice
@Slf4j
public class FlowExceptionHandler {
    @ExceptionHandler(FlowException.class)
    public Result flowExceptionHandler(Exception e){
        log.info("flowExceptionHandler: {}", e);
        if (e.getMessage().endsWith(ResultEnum.ERROR_NOTFOUND.getMsg())){
            return Result.fail(ResultEnum.ERROR_NOTFOUND);
        } else if (e.getMessage().endsWith(ResultEnum.ERROR_OPERATION.getMsg())) {
            return Result.fail(ResultEnum.ERROR_OPERATION);
        }
        return Result.fail();
    }

    @ExceptionHandler(Exception.class)
    public Result serverErrorHandler(Exception e){
        return Result.fail();
    }
}
