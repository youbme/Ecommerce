package com.example.ecomfinal.controller;


import com.example.ecomfinal.exception.UserNotFoundException;
import com.example.ecomfinal.model.LoginRequest;
import com.example.ecomfinal.model.RefreshToken;
import com.example.ecomfinal.model.TokenResponse;
import com.example.ecomfinal.model.User;
import com.example.ecomfinal.repository.RefreshTokenRepository;
import com.example.ecomfinal.repository.UserRepository;
import com.example.ecomfinal.security.JwtTokenUtil;
import com.example.ecomfinal.service.RefreshTokenService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.Ref;
import java.time.Instant;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.impl.DefaultClaims;


@RestController
@CrossOrigin("http://localhost:3000")
public class LoginController {

    @Autowired
    JwtTokenUtil jwt;

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RefreshTokenService refreshTokenService;


    @GetMapping("/test")
    public String test(){
        return "pass!!!!!!sdff";
    }

    @GetMapping("/login/next")
    public String loginnext(){
        return "login next";
    }

    @GetMapping("/test/next")
    public String testnext(){
        return "testt next";
    }

    @PostMapping("/authenticate")
    public TokenResponse authenticateuserandgenerateToken(@RequestBody LoginRequest loginRequest) throws Exception {
//
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            
            if (authentication.isAuthenticated()) {

                String refreshtoken=  jwt.generateRefreshToken(loginRequest.getUsername());


                refreshTokenService.saverefreshtoken(refreshtoken);

                TokenResponse tok = new TokenResponse();
                tok.setAccessToken(jwt.generateToken(loginRequest.getUsername()));
                tok.setRefreshToken(refreshtoken);
                return tok;


            } else {
                throw new UserNotFoundException("invalid user");
            }
        }catch (DisabledException e){
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


    @GetMapping("/refreshtoken")
    public String refreshtoken(HttpServletRequest request) {
        // From the HttpRequest get the claims
        String accesstoken = null;
        User user = null;
      //  DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");

        String refreshtoken = request.getHeader("Authorization").replace("Bearer ","");
        System.out.println("/*/*/*"+refreshtoken);


        Date expiry = jwt.getExpirationDateFromToken(refreshtoken);
        System.out.println("///////////"+expiry);
        //checking accesstoken expiry

        RefreshToken dbrefreshtoken = refreshTokenRepository.findByToken(refreshtoken)
                .orElseThrow(()-> new UserNotFoundException(refreshtoken));
        if(expiry.compareTo(Date.from(Instant.now())) <0) {

            System.out.println("-----------");

            refreshTokenService.deleterefreshtoken(refreshtoken);
            System.out.println("REfreshtoken has expired");
        }

       User user1= dbrefreshtoken.getUser();
       String token= jwt.generatetokenFromUsername(user1.getUsername());

    return token;
    }

    public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
        Map<String, Object> expectedMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            expectedMap.put(entry.getKey(), entry.getValue());
            System.out.println(expectedMap);
        }
        return expectedMap;
    }


}
