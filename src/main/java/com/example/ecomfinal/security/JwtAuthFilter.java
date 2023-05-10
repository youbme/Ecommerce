package com.example.ecomfinal.security;

import com.example.ecomfinal.service.RefreshTokenService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtService;

    @Autowired
    private UserSpringService userSpringService;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
          String token= authorizetoken(request);



        } catch (ExpiredJwtException ex) {

//            System.out.println( "----------"+request.getHeader("isRefreshToken"));
//
            String isRefreshToken = request.getHeader("isRefreshToken");
            String requestURL = request.getRequestURI().toString();
//            System.out.println(requestURL);
//            //allow for Refresh Token creation if following conditions are true
            if (isRefreshToken != null && isRefreshToken.equals("true") && requestURL.contains("refreshtoken")) {
               // refreshTokenService.deleterefreshtoken();
                String authHeader = request.getHeader("Authorization");
                String token = null;

                String refreshtoken = null;
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    token = authHeader.substring(7);
                }
                refreshTokenService.deleterefreshtoken(token);
                System.out.println("YOur session expired");
            }
//

        }
        filterChain.doFilter(request, response);
    }

    public String authorizetoken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        String refreshtoken = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtService.getUsernameFromToken(token);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userSpringService.loadUserByUsername(username);
            if (jwtService.validateToken(token, userDetails)) {
//                refreshtoken = jwtService.generateRefreshToken(token);
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);

            }
        }
        return  token;
    }
}


