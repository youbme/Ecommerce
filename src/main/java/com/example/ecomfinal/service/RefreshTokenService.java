package com.example.ecomfinal.service;

import com.example.ecomfinal.model.RefreshToken;
import com.example.ecomfinal.model.User;
import com.example.ecomfinal.repository.RefreshTokenRepository;
import com.example.ecomfinal.repository.UserRepository;
import com.example.ecomfinal.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class RefreshTokenService {

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    JwtTokenUtil jwt;
    @Autowired
    UserRepository userRepository;


    public void saverefreshtoken(String refreshToken){
        Date expirydate = jwt.getExpirationDateFromToken(refreshToken);
        String user1 = jwt.getUsernameFromToken(refreshToken);
        User user = userRepository.findByName(user1)
                .orElseThrow();
        RefreshToken refreshTok = new RefreshToken();
        refreshTok.setUser(user);
        refreshTok.setToken(refreshToken);
        refreshTok.setExpiryDate(expirydate);
        refreshTokenRepository.save(refreshTok);
    }

    public Optional<RefreshToken> deleterefreshtoken(String refreshtoken){
       Optional<RefreshToken> token = refreshTokenRepository.findByToken(refreshtoken);
        refreshTokenRepository.deleteByToken(refreshtoken);
        return  token;
    }
}
