package com.example.ecomfinal.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.SecondaryTable;

@Getter
@Setter
public class TokenResponse {

    String accessToken;
    String refreshToken;
}
