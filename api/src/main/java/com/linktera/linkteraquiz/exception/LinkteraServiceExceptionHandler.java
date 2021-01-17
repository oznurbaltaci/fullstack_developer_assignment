package com.linktera.linkteraquiz.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Component
public class LinkteraServiceExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handle(Exception exception, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        LinkteraServiceException globalExceptionMessage;
        if (exception instanceof LinkteraServiceException && ((LinkteraServiceException) exception).getCode() != null) {
            globalExceptionMessage = (LinkteraServiceException) exception;
        } else {
            globalExceptionMessage = new LinkteraServiceException(ErrorCode.MISSING_PARAM);
        }
        return new ResponseEntity<>(globalExceptionMessage, headers, globalExceptionMessage.getStatus());
    }
}
