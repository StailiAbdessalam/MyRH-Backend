package com.veinsmoke.myrhbackend.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.veinsmoke.myrhbackend.entity.Company;
import com.veinsmoke.myrhbackend.error.exception.auth.RegisterFailedException;
import com.veinsmoke.myrhbackend.error.exception.auth.VerificationFailedException;
import com.veinsmoke.myrhbackend.helper.JwtUtil;
import com.veinsmoke.myrhbackend.helper.MailSenderService;
import com.veinsmoke.myrhbackend.mapstruct.dtos.CompanyPostDto;
import com.veinsmoke.myrhbackend.mapstruct.mappers.CompanyMapper;
import com.veinsmoke.myrhbackend.model.request.VerificationCredentials;
import com.veinsmoke.myrhbackend.model.response.ResponseMessage;
import com.veinsmoke.myrhbackend.service.company.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

import java.time.LocalDateTime;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisterController {

    private final CompanyService companyService;
    private final CompanyMapper companyMapper;
    private final MailSenderService mailSender;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;




    @PostMapping
    public ResponseEntity<HashMap<String, String>> register(@Valid @RequestBody CompanyPostDto companyPostDto){
        // check if email already exists
        companyService.getCompanyByEmail(companyPostDto.getEmail()).ifPresent(company -> {
            throw new RegisterFailedException("Email already in use");
        });

        companyPostDto.setPassword(passwordEncoder.encode(companyPostDto.getPassword()));

        // prepare company
        Company companyToSave = companyMapper.companyPostDtoToCompany(companyPostDto);
        companyToSave.setVerificationCode(generateCode());
        companyToSave.setSentAt(LocalDateTime.now());

        // save company
        companyService.save(companyToSave);
        mailSender.send(companyPostDto.getEmail(), "Welcome to MyRH", "Your verification code is: " + companyToSave.getVerificationCode());

        // return response
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Verify your account by providing the code sent to your email");
        response.put("email", companyPostDto.getEmail());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify")
    public ResponseEntity<HashMap<String, String>> verify(@RequestBody VerificationCredentials verificationCredentials){
        String email = verificationCredentials.getEmail();
        String code = verificationCredentials.getCode();

        // check if email exists
        Company company = companyService.getCompanyByEmail(email).orElseThrow(() -> new VerificationFailedException("Email not found"));

        // check if code is correct
        if(!company.getVerificationCode().equals(code)){
            throw new VerificationFailedException("Invalid verification code");
        }

        // check if code is expired
        if(company.getSentAt().plusMinutes(3).isBefore(LocalDateTime.now())){
            throw new VerificationFailedException("Verification code expired");
        }

        // update company
        company.setVerified(true);
        companyService.save(company);

        // authenticate new client
        String emailAndType = company.getEmail() + ":company";

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(emailAndType, verificationCredentials.getPassword());
        authenticationManager.authenticate(token);
        UserDetails user = userDetailsService.loadUserByUsername(emailAndType);

        // generate jwt token
        String jwtToken = jwtUtil.generateToken(
                new HashMap<>() {{
                    put("userType", "company");
                }},
                user);



        // return response
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Account verified Successfully");
        response.put("email", verificationCredentials.getEmail());
        response.put("token", jwtToken);
        return ResponseEntity.ok(response);
    }


    private String generateCode() {
        String code = String.valueOf((int) Math.floor(Math.random() * 1000000));
        if(code.length() < 6){
            code = "0" + code;
        }
        return code;
    }

}
