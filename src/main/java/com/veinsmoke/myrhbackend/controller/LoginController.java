package com.veinsmoke.myrhbackend.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.veinsmoke.myrhbackend.entity.Agent;
import com.veinsmoke.myrhbackend.error.exception.auth.LoginFailedException;
import com.veinsmoke.myrhbackend.helper.JwtUtil;
import com.veinsmoke.myrhbackend.model.request.LoginCredentials;
import com.veinsmoke.myrhbackend.repository.AgentRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    final AuthenticationManager authenticationManager;
    final UserDetailsService userDetailsService;
    final PasswordEncoder passwordEncoder;
    final JwtUtil jwtUtil;
    final AgentRepository agentRepository;



    private ResponseEntity<HashMap<String, String>> login(LoginCredentials loginCredentials, String userType) throws LoginFailedException {
        log.info("Login request received for user {}", loginCredentials.getEmail());

        String emailAndType = loginCredentials.getEmail()+ ":" + userType;
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(emailAndType, loginCredentials.getPassword()));
        } catch (Exception e) {
            throw new LoginFailedException("Login failed for user " + loginCredentials.getEmail());
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(emailAndType, loginCredentials.getPassword());
        authenticationManager.authenticate(token);
        UserDetails user = userDetailsService.loadUserByUsername(emailAndType);

        String jwtToken = jwtUtil.generateToken(
                new HashMap<>() {{
                    put("userType", userType);
                }},
                user);
        HashMap<String, String> response = new HashMap<>();
        response.put("token", jwtToken);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/agent")
    public ResponseEntity<HashMap<String, String>> loginAgent(@Valid @RequestBody LoginCredentials loginCredentials) throws LoginFailedException {
        return login(loginCredentials, "agent");
    }

    @PostMapping("/company")
    public ResponseEntity<HashMap<String, String>> loginCompany(@Valid @RequestBody LoginCredentials loginCredentials) throws LoginFailedException {
        return login(loginCredentials, "company");
    }

    @PostMapping("/encrypt")
    public ResponseEntity<String> encrypt(@RequestBody ObjectNode objectNode) {
        return ResponseEntity.ok(passwordEncoder.encode(objectNode.get("password").asText()));
    }

}
