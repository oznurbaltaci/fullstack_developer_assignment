package com.linktera.linkteraquiz.web.advice;


import com.linktera.linkteraquiz.exception.ErrorCode;
import com.linktera.linkteraquiz.exception.LinkteraServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ResponseBody
@ControllerAdvice
public class CommonControllerAdvice {

    @ExceptionHandler(LinkteraServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleCommonServiceException(LinkteraServiceException ex) {
        return ErrorResponse.builder().message(ex.getMessage()).code(ex.getCode()).build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(Exception ex, HttpServletRequest httpServletRequest) {
        log.error(httpServletRequest.getServletPath(), ex);
        return ErrorResponse.builder().message(ErrorCode.GENERAL_EXCEPTION.getMessage()).code(ErrorCode.ARGUMENT_NOT_VALID.getCode()).build();
    }

}
