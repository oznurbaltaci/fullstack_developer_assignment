package com.linktera.linkteraquiz.model;

 import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenModel {
    private String token;
    private int userType;
}
