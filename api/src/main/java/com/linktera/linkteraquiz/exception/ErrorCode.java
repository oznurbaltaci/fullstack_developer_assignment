package com.linktera.linkteraquiz.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    GENERAL_EXCEPTION(1000, "Sistemde bir hata oluştu.", HttpStatus.BAD_REQUEST),
    ARGUMENT_NOT_VALID(1001, "Zorunlu parametreler boş olamaz", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1002,"Kullanıcı bulunamadı.", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(401,"İşlem yapabilmeniz için giriş yapmanız gerekmektedir.", HttpStatus.BAD_REQUEST),
    FORBIDDEN(403,"İşlem yapabilmeniz için giriş yapmanız gerekmektedir.", HttpStatus.BAD_REQUEST),
    USERNAME_EXIST(1003,"Bu usernameli kullanıcı bulunmaktadir.", HttpStatus.BAD_REQUEST),
    CANNOT_BE_RENTED(1004,"Istenilen kitap stokta mevcut degildir.", HttpStatus.BAD_REQUEST),
    LOGIN_REQUEST_INCORRECT(1005,"Kullanıcı adı veya şifre yanlış.", HttpStatus.BAD_REQUEST),
    FORBIDDEN_OPERATION(1007,"Bu islem yapilamaz.", HttpStatus.BAD_REQUEST),
    USER_DELETED(1006,"Kullanıcı pasifte.", HttpStatus.BAD_REQUEST),
    USER_ACCOUNT_NOT_FOUND(1008,"Kullanıcı hesabı bulunamadı.", HttpStatus.BAD_REQUEST),
    DUPLICATE_ENTRY(1009,"Mukerrem Islem.", HttpStatus.BAD_REQUEST),
    WRONG_HTTP_METHOD(1013,"Yanlış HTTP metodu kullandınız. Lütfen kontrol edin!", HttpStatus.BAD_REQUEST),
    MISSING_HEADER(1018,"Eksik Request Header. Lütfen kontrol ediniz.", HttpStatus.BAD_REQUEST),
    MISSING_PARAM(1019,"Eksik Request Parameter. Lütfen kontrol ediniz.", HttpStatus.BAD_REQUEST),
    TOKEN_NOT_FOUND(1021,"Token bulunamadı.", HttpStatus.BAD_REQUEST);

    private Integer code;
    private String message;
    private HttpStatus status;
}
