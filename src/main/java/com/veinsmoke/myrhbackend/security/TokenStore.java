package com.veinsmoke.myrhbackend.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class TokenStore {

    private final Map<String, Authentication> tokenStore = new HashMap<>();

    public String generateToken( Authentication authentication){
        String token = UUID.randomUUID().toString();
        tokenStore.put(token, authentication);
        return token;
    }

    public Authentication getAuthentication(String token){
        return tokenStore.getOrDefault(token, null);
    }

}
