package com.example.ecomfinal.security;

import com.example.ecomfinal.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserSpringDetails implements UserDetails {

   private String username;
   private String password;;
   private List<GrantedAuthority> authorities;

   public  UserSpringDetails (User user){
       username=user.getUsername();
       password=user.getPassword();
       authorities= Arrays.stream(user.getRole().split(","))
               .map(SimpleGrantedAuthority::new)
               .collect(Collectors.toList());
   }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


//    public UserSpringDetails(User user) {
//        this.username= user.getUsername();
//        this.password = user.getPassword();
//        this.authority = (GrantedAuthority) Arrays.stream(user.getRole().split(","))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());;
//    }


}
