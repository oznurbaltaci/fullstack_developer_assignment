package com.linktera.linkteraquiz.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class LinkteraServiceException extends RuntimeException {

    private Integer code;
    private String message;
    private HttpStatus status;

    public LinkteraServiceException(ErrorCode errorCode) {
        super();
        this.code = errorCode.getCode();
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
