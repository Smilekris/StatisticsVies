package com.smile.monkeyserver.config;

import com.smile.monkeyapi.enitity.ResponseResult;
import com.smile.monkeyserver.exception.MonkeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName MonkeyExceptionHandler
 * @Author kris
 * @Date 2019/8/14
 **/
@ControllerAdvice
public class MonkeyExceptionHandler {

    @ExceptionHandler(MonkeyException.class)
    @ResponseBody
    ResponseEntity<?> handleControllerException(HttpServletRequest request,MonkeyException ex) {
        return new ResponseEntity<>(ResponseResult.ResultHelper.instance().setCode(ex.getCode()).setMsg(ex.getMsg()), HttpStatus.OK);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
