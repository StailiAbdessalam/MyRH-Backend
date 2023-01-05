package com.veinsmoke.myrhbackend.security;

import com.veinsmoke.myrhbackend.entity.Agent;
import com.veinsmoke.myrhbackend.entity.Company;
import com.veinsmoke.myrhbackend.repository.AgentRepository;
import com.veinsmoke.myrhbackend.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
public class CostumeUserDetailsService implements UserDetailsService {

    private final CompanyRepository companyRepository;
    private final AgentRepository agentRepository;

    @Override
    public UserDetails loadUserByUsername(String emailAndType) throws UsernameNotFoundException {
        log.info("Inside UserDetailService for user {}", emailAndType);
        String email    = emailAndType.split(":")[0];
        String type     = emailAndType.split(":")[1];

        switch (type) {
            case "company" -> {
                Company company = companyRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Company not found"));
                company.setPassword(company.getPassword().trim());
                return new User(email, company.getPassword(), Collections.singleton(new SimpleGrantedAuthority("COMPANY")));
            }
            case "agent" -> {
                Agent agent = agentRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Agent not found"));
                agent.setPassword(agent.getPassword().trim());
                return new User(email, agent.getPassword(), Collections.singleton(new SimpleGrantedAuthority("AGENT")));
            }
            default -> throw new UsernameNotFoundException("User not found");
        }
    }
}
